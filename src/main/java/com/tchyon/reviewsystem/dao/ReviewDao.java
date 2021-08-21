package com.tchyon.reviewsystem.dao;

import com.tchyon.reviewsystem.pojo.*;
import com.tchyon.reviewsystem.utility.ActingDBUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 21-08-2021
 */
@Repository
public class ReviewDao {

  private final Logger logger = LoggerFactory.getLogger(ReviewDao.class);

  @Autowired private ActingDBUtility utility;

  private int reviewGeneratorId = 0;

  public int userReviewCount(int userId) {
    Map<Integer, List<Integer>> userReviewMap = utility.getUserReviewMap();
    if (userReviewMap.containsKey(userId)) {
      return userReviewMap.get(userId).size();
    }
    return 0;
  }

  public boolean reviewExistsForPlatformAndUser(int userId, int platformId) {
    Map<Integer, List<Integer>> userReviewMap = utility.getUserReviewMap();
    if (!userReviewMap.containsKey(userId)) {
      return false;
    }
    Map<Integer, Integer> reviewPlatformMap = utility.getReviewPlatformMap();
    List<Integer> reviewIds = userReviewMap.get(userId);
    logger.debug("--> reviewPlatformMap : '{}'", reviewPlatformMap);
    return reviewIds.stream()
        .anyMatch(
            reviewId ->
                reviewPlatformMap.containsKey(reviewId)
                    && platformId == reviewPlatformMap.get(reviewId));
  }

  public int createReview(UserPojo userPojo, PlatformPojo platformPojo, int review) {
    int userId = userPojo.getUserId();
    int platformId = platformPojo.getPlatformId();
    return this.addReview(userId, platformId, review, userPojo.getLevel());
  }

  public int addReview(int userId, int platformId, int review, ReviewerLevel level) {
    int weightage = this.getWeightageForLevel(level);
    int currentId = ++reviewGeneratorId;
    int score = review * weightage;
    ReviewPojo reviewPojo = new ReviewPojo(currentId, userId, platformId, score, level, new Date());
    Map<Integer, ReviewPojo> reviewList = utility.getReviewMapList();
    reviewList.put(currentId, reviewPojo);
    this.reviewUserIndex(userId, currentId);
    this.reviewPlatformIndex(currentId, platformId);
    logger.debug(
        "ReviewDao :: addReview : user : '{}' has reviewed platform : '{}' with score : '{}' and weightage : '{}x'",
        userId,
        platformId,
        review,
        weightage);
    return currentId;
  }

  public boolean reviewUserIndex(int userId, int reviewId) {
    Map<Integer, List<Integer>> userReviewMap = utility.getUserReviewMap();
    userReviewMap.computeIfPresent(
        userId,
        (usrId, reviewIds) -> {
          reviewIds.add(reviewId);
          return reviewIds;
        });

    userReviewMap.computeIfAbsent(
        userId,
        usrId -> {
          List<Integer> reviewIds = new ArrayList<>();
          reviewIds.add(reviewId);
          return reviewIds;
        });
    logger.debug(
        "ReviewDao :: reviewPlatformIndex : review : '{}' mapped successfully with user : '{}' ",
        reviewId,
        userId);
    return true;
  }

  public boolean reviewPlatformIndex(int reviewId, int platformId) {
    Map<Integer, Integer> reviewPlatformMap = utility.getReviewPlatformMap();
    reviewPlatformMap.put(reviewId, platformId);
    logger.debug(
        "ReviewDao :: reviewPlatformIndex : review : '{}' mapped successfully with platform : '{}' ",
        reviewId,
        platformId);
    return true;
  }

  public int getWeightageForLevel(ReviewerLevel reviewerLevel) {
    logger.debug("ReviewDao :: getWeightageForLevel : finding weight for '{}'", reviewerLevel);
    return utility.getReviewerWeightage().get(reviewerLevel);
  }

  public List<ViewReviewPojo> getViewReview() {
    Map<Integer, ReviewPojo> reviewList = utility.getReviewMapList();
    Map<Integer, Integer> reviewPlatformMap = utility.getReviewPlatformMap();
    Map<Integer, List<Integer>> userReviewMap = utility.getUserReviewMap();
    Map<String, Integer> userIdMap = utility.getUserIdMap();
    Map<Integer, PlatformPojo> platformList = utility.getPlatformMapList();
    Map<Integer, VerticalPojo> verticalList = utility.getVerticalMapList();

    List<ViewReviewPojo> viewReviewList = new ArrayList<>();

    userReviewMap.forEach(
        (userId, reviewIds) -> {
          String userName =
              userIdMap.entrySet().stream()
                  .filter(entry -> entry.getValue().equals(userId))
                  .map(entry -> entry.getKey())
                  .findAny()
                  .orElse("NA");
          reviewIds.forEach(
              reviewId -> {
                ReviewPojo reviewPojo = reviewList.get(reviewId);
                PlatformPojo platformPojo = platformList.get(reviewPlatformMap.get(reviewId));
                VerticalPojo verticalPojo = verticalList.get(platformPojo.getVerticalId());
                ViewReviewPojo viewReview =
                    new ViewReviewPojo(
                        reviewId,
                        userId,
                        userName,
                        reviewPojo.getReviewerLevel(),
                        platformPojo.getPlatformId(),
                        platformPojo.getPlatformName(),
                        verticalPojo.getVerticalId(),
                        verticalPojo.getVerticalName(),
                        reviewPojo.getReview(),
                        reviewPojo.getReviewCreatedOn());

                viewReviewList.add(viewReview);
              });
        });
    return viewReviewList;
  }
}
