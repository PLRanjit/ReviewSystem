package com.tchyon.reviewsystem.pojo;

import java.util.Date;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
public class ReviewPojo {

  private int reviewId;
  private int reviewerId;
  private int platformId;
  private int review;
  private ReviewerLevel reviewerLevel;
  private Date reviewCreatedOn;

  public ReviewPojo() {}

  public ReviewPojo(
      int reviewId,
      int reviewerId,
      int platformId,
      int review,
      ReviewerLevel reviewerLevel,
      Date reviewCreatedOn) {
    this.reviewId = reviewId;
    this.reviewerId = reviewerId;
    this.platformId = platformId;
    this.review = review;
    this.reviewerLevel = reviewerLevel;
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

  public int getPlatformId() {
    return platformId;
  }

  public void setPlatformId(int platformId) {
    this.platformId = platformId;
  }

  public int getReview() {
    return review;
  }

  public void setReview(int review) {
    this.review = review;
  }

  public ReviewerLevel getReviewerLevel() {
    return reviewerLevel;
  }

  public void setReviewerLevel(ReviewerLevel reviewerLevel) {
    this.reviewerLevel = reviewerLevel;
  }

  public Date getReviewCreatedOn() {
    return reviewCreatedOn;
  }

  public void setReviewCreatedOn(Date reviewCreatedOn) {
    this.reviewCreatedOn = reviewCreatedOn;
  }

  @Override
  public String toString() {
    return "ReviewPojo{"
        + "reviewId="
        + reviewId
        + ", reviewerId="
        + reviewerId
        + ", platformId="
        + platformId
        + ", review="
        + review
        + ", reviewerLevel="
        + reviewerLevel
        + ", reviewCreatedOn="
        + reviewCreatedOn
        + '}';
  }
}
