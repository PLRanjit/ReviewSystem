package com.tchyon.reviewsystem.service;

import com.tchyon.reviewsystem.Exceptions.PlatformException;
import com.tchyon.reviewsystem.Exceptions.ReviewException;
import com.tchyon.reviewsystem.Exceptions.UserException;
import com.tchyon.reviewsystem.dao.PlatformDao;
import com.tchyon.reviewsystem.dao.ReviewDao;
import com.tchyon.reviewsystem.dao.UserDao;
import com.tchyon.reviewsystem.pojo.PlatformPojo;
import com.tchyon.reviewsystem.pojo.ReviewerLevel;
import com.tchyon.reviewsystem.pojo.UserPojo;
import com.tchyon.reviewsystem.pojo.ViewReviewPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
@Service
public class ReviewService {

  private final Logger logger = LoggerFactory.getLogger(ReviewService.class);

  @Autowired private UserDao userDao;

  @Autowired private PlatformDao platformDao;

  @Autowired private ReviewDao reviewDao;

  @Value("${app.viewer.upgrade.threshold}")
  private int viewerThreshold;

  public int addReview(String userName, String platformName, int review)
      throws UserException, PlatformException, ReviewException {
    logger.debug(
        "ReviewService :: addReview :: userName : {} , platformName : {}, reviewScore : {}",
        userName,
        platformName,
        review);
    if (review > 5 || review < 1) {
      throw new ReviewException("Review can't be less than 1 and greater than 5");
    }
    UserPojo userPojo = userDao.getUserByUserName(userName);
    PlatformPojo platformPojo = platformDao.getPlatformByName(platformName);
    boolean isReviewEnabled = this.isReviewEnabled(platformPojo);
    logger.debug("ReviewService :: addReview : userPojo : {} ", userPojo);
    logger.debug("ReviewService :: addReview : platformPojo : {} ", platformPojo);
    if (!isReviewEnabled) {
      throw new ReviewException(String.format("'%s' platform yet to be release", platformName));
    }

    boolean reviewExists =
        reviewDao.reviewExistsForPlatformAndUser(
            userPojo.getUserId(), platformPojo.getPlatformId());
    if (reviewExists) {
      throw new ReviewException("Multiple review not allowed for platforms from single user");
    }

    int reviewId = reviewDao.createReview(userPojo, platformPojo, review);
    int userId = userPojo.getUserId();
    ReviewerLevel level = userPojo.getLevel();
    this.upgradeUser(userId, level);
    return reviewId;
  }

  private boolean upgradeUser(int userId, ReviewerLevel level) throws UserException {
    logger.debug("ReviewService :: upgradeUser :: userId : {} , ReviewerLevel : {}", userId, level);
    int userReviewCount = reviewDao.userReviewCount(userId);
    if (userReviewCount >= viewerThreshold && level == ReviewerLevel.VIEWER) {
      return userDao.upgradeUser(userId, viewerThreshold, ReviewerLevel.CRITIC);
    }
    return false;
  }

  public boolean isReviewEnabled(PlatformPojo platformPojo) {
    logger.debug("ReviewService :: isReviewEnabled :: platformPojo : {}", platformPojo);
    return platformPojo.getReleaseDate().isBefore(LocalDateTime.now());
  }

  public List<ViewReviewPojo> getViewReview() {
    logger.debug("ReviewService :: getViewReview");
    return reviewDao.getViewReview();
  }
}
