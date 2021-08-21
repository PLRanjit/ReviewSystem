package com.tchyon.reviewsystem.pojo;

import java.util.Date;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 21-08-2021
 */
public class ViewReviewPojo {

  private int reviewId;
  private int reviewerId;
  private String reviewerName;
  private ReviewerLevel level;
  private int platformId;
  private String platformName;
  private int verticalId;
  private String verticalName;
  private int reviewScore;
  private Date reviewCreatedOn;

  public ViewReviewPojo() {}

  public ViewReviewPojo(
      int reviewId,
      int reviewerId,
      String reviewerName,
      ReviewerLevel level,
      int platformId,
      String platformName,
      int verticalId,
      String verticalName,
      int reviewScore,
      Date reviewCreatedOn) {
    this.reviewId = reviewId;
    this.reviewerId = reviewerId;
    this.reviewerName = reviewerName;
    this.level = level;
    this.platformId = platformId;
    this.platformName = platformName;
    this.verticalId = verticalId;
    this.verticalName = verticalName;
    this.reviewScore = reviewScore;
    this.reviewCreatedOn = reviewCreatedOn;
  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public int getReviewerId() {
    return reviewerId;
  }

  public void setReviewerId(int reviewerId) {
    this.reviewerId = reviewerId;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public ReviewerLevel getLevel() {
    return level;
  }

  public void setLevel(ReviewerLevel level) {
    this.level = level;
  }

  public int getPlatformId() {
    return platformId;
  }

  public void setPlatformId(int platformId) {
    this.platformId = platformId;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public int getVerticalId() {
    return verticalId;
  }

  public void setVerticalId(int verticalId) {
    this.verticalId = verticalId;
  }

  public String getVerticalName() {
    return verticalName;
  }

  public void setVerticalName(String verticalName) {
    this.verticalName = verticalName;
  }

  public int getReviewScore() {
    return reviewScore;
  }

  public void setReviewScore(int reviewScore) {
    this.reviewScore = reviewScore;
  }

  public Date getReviewCreatedOn() {
    return reviewCreatedOn;
  }

  public void setReviewCreatedOn(Date reviewCreatedOn) {
    this.reviewCreatedOn = reviewCreatedOn;
  }

  @Override
  public String toString() {
    return "\nViewReviewPojo{"
        + "reviewId="
        + reviewId
        + ", reviewerId="
        + reviewerId
        + ", reviewerName='"
        + reviewerName
        + '\''
        + ", level="
        + level
        + ", platformId="
        + platformId
        + ", platformName='"
        + platformName
        + '\''
        + ", verticalId="
        + verticalId
        + ", verticalName='"
        + verticalName
        + '\''
        + ", reviewScore="
        + reviewScore
        + ", reviewCreatedOn="
        + reviewCreatedOn
        + '}';
  }
}
