package com.tchyon.reviewsystem.utility;

import com.tchyon.reviewsystem.pojo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.HashMap;
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
public class ActingDBUtility {

  private Map<String, Integer> userIdMap = new HashMap<>();
  private Map<Integer, UserPojo> userMapList = new HashMap<>();

  private Map<String, Integer> verticalIdMap = new HashMap<>();
  private Map<String, Integer> platformIdMap = new HashMap<>();
  private Map<Integer, Integer> platformVerticalMap = new HashMap<>();
  private Map<Integer, PlatformPojo> platformMapList = new HashMap<>();
  private Map<Integer, VerticalPojo> verticalMapList = new HashMap<>();

  private Map<Integer, List<Integer>> userReviewMap = new HashMap<>();
  private Map<Integer, Integer> reviewPlatformMap = new HashMap<>();
  private Map<Integer, ReviewPojo> reviewMapList = new HashMap<>();
  private EnumMap<ReviewerLevel, Integer> reviewerWeightage = new EnumMap<>(ReviewerLevel.class);

  @Value("${app.review.level.viewer.weightage}")
  private int viewerWeightAge;

  @Value("${app.review.level.critic.weightage}")
  private int criticWeightAge;

  @PostConstruct
  private void reviewerWeightage() {
    this.reviewerWeightage.put(ReviewerLevel.VIEWER, viewerWeightAge);
    this.reviewerWeightage.put(ReviewerLevel.CRITIC, criticWeightAge);
  }

  /** @return review list with reviewId as key and reviewpojo as */
  public Map<Integer, ReviewPojo> getReviewMapList() {
    return reviewMapList;
  }

  public void setReviewMapList(Map<Integer, ReviewPojo> reviewMapList) {
    this.reviewMapList = reviewMapList;
  }

  public Map<ReviewerLevel, Integer> getReviewerWeightage() {
    return reviewerWeightage;
  }

  public Map<Integer, List<Integer>> getUserReviewMap() {
    return userReviewMap;
  }

  public void setUserReviewMap(Map<Integer, List<Integer>> userReviewMap) {
    this.userReviewMap = userReviewMap;
  }

  public Map<Integer, Integer> getReviewPlatformMap() {
    return reviewPlatformMap;
  }

  public void setReviewPlatformMap(Map<Integer, Integer> reviewPlatformMap) {
    this.reviewPlatformMap = reviewPlatformMap;
  }

  public Map<Integer, UserPojo> getUserMapList() {
    return userMapList;
  }

  public void setUserMapList(Map<Integer, UserPojo> userMapList) {
    this.userMapList = userMapList;
  }

  public Map<Integer, VerticalPojo> getVerticalMapList() {
    return verticalMapList;
  }

  public void setVerticalMapList(Map<Integer, VerticalPojo> verticalMapList) {
    this.verticalMapList = verticalMapList;
  }

  public Map<Integer, PlatformPojo> getPlatformMapList() {
    return platformMapList;
  }

  public void setPlatformMapList(Map<Integer, PlatformPojo> platformMapList) {
    this.platformMapList = platformMapList;
  }

  public Map<String, Integer> getUserIdMap() {
    return userIdMap;
  }

  public void setUserIdMap(Map<String, Integer> userIdMap) {
    this.userIdMap = userIdMap;
  }

  public Map<String, Integer> getVerticalIdMap() {
    return verticalIdMap;
  }

  public void setVerticalIdMap(Map<String, Integer> verticalIdMap) {
    this.verticalIdMap = verticalIdMap;
  }

  public Map<String, Integer> getPlatformIdMap() {
    return platformIdMap;
  }

  public void setPlatformIdMap(Map<String, Integer> platformIdMap) {
    this.platformIdMap = platformIdMap;
  }

  public Map<Integer, Integer> getPlatformVerticalMap() {
    return platformVerticalMap;
  }

  public void setPlatformVerticalMap(Map<Integer, Integer> platformVerticalMap) {
    this.platformVerticalMap = platformVerticalMap;
  }
}
