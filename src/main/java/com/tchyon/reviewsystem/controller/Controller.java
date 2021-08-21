package com.tchyon.reviewsystem.controller;

import com.tchyon.reviewsystem.Exceptions.PlatformException;
import com.tchyon.reviewsystem.Exceptions.ReviewException;
import com.tchyon.reviewsystem.Exceptions.UserException;
import com.tchyon.reviewsystem.pojo.UserPojo;
import com.tchyon.reviewsystem.pojo.ViewPlatformPojo;
import com.tchyon.reviewsystem.pojo.ViewReviewPojo;
import com.tchyon.reviewsystem.service.PlatformService;
import com.tchyon.reviewsystem.service.ReviewService;
import com.tchyon.reviewsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
@Component
public class Controller {

  @Autowired private UserService userService;

  @Autowired private PlatformService platformService;

  @Autowired private ReviewService reviewService;

  /**
   * Add New User to System
   *
   * @param userName - Unique Name for the user
   * @return - id of newly created user
   * @throws UserException - throws exception if user already exists or unable to create user
   */
  public int addUser(@NonNull String userName) throws UserException {
    return userService.createUser(userName);
  }

  /**
   * Add new Platform with vertical to system
   *
   * @param platformName - platform to be created
   * @param verticalName - vertical in which platform belongs
   * @return - id of newly created platform
   * @throws PlatformException - throws if platform already exists or unable to create platform or
   *     vertical
   */
  public int addPlatform(@NonNull String platformName, @NonNull String verticalName)
      throws PlatformException {
    return platformService.addPlatform(platformName, verticalName, null);
  }

  /**
   * Add new Platform with vertical and release date to system
   *
   * @param platformName - platform to be created
   * @param verticalName - vertical in which platform belongs
   * @param releaseDate - release date for the platform
   * @return - id of newly created platform
   * @throws PlatformException - throws if platform already exists or unable to create platform or
   *     vertical
   */
  public int addPlatform(
      @NonNull String platformName, @NonNull String verticalName, LocalDateTime releaseDate)
      throws PlatformException {
    return platformService.addPlatform(platformName, verticalName, releaseDate);
  }
  /**
   * Add Review for platform in system
   *
   * <p>If the reviewer is critic it's 2x score wil be stored
   *
   * @param userName - reviewer username (Unique name of user in system )
   * @param platformName - platform name which user is reviewing
   * @param review - review score for the platform
   * @return - id of newly created review
   * @throws UserException - if user does not exist
   * @throws PlatformException - if platform does not exist
   * @throws ReviewException - if user tries to add multiple review for same platform or platform is
   *     not released yet or unable to create review
   */
  public int addReview(@NonNull String userName, @NonNull String platformName, int review)
      throws UserException, PlatformException, ReviewException {
    return reviewService.addReview(userName, platformName, review);
  }

  /**
   * Calculates average for all the platforms avaiable in system.
   *
   * <p>If review is not present then average will be shown '0' for that platform
   *
   * @return - platform name and it's average score
   */
  public Map<String, Double> averagePlatformScore() {
    return platformService.averagePlatformScore();
  }

  /**
   * Top 'N' platform's review from 'critics' level Viewer of Specified Vertical.
   *
   * <p><b>Note : </b>Non Critic level review count will be excluded
   *
   * @param n - number of platform to be fetched
   * @param verticalName - vertical name to be searched for
   * @return - platform with total review score
   */
  public Map<String, Integer> topNReviewTotalByCriticsInVertical(
      int n, @NonNull String verticalName) {
    return platformService.topNReviewTotalByCriticsInVertical(n, verticalName);
  }

  /**
   * Shows all the platform info
   *
   * @return - platforms
   */
  public List<ViewPlatformPojo> showAllPlatform() {
    return platformService.showPlatforms();
  }

  /**
   * Shows all the review info
   *
   * @return - review
   */
  public List<ViewReviewPojo> getViewReview() {
    return reviewService.getViewReview();
  }

  /**
   * Get All Users from system
   *
   * @return users
   */
  public List<UserPojo> getAllUsers() {
    return userService.getAllUsers();
  }
}
