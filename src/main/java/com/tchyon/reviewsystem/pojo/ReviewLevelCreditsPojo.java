package com.tchyon.reviewsystem.pojo;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
public class ReviewLevelCreditsPojo {
  private ReviewerLevel level;
  private int credits;

  public ReviewLevelCreditsPojo() {}

  public ReviewLevelCreditsPojo(ReviewerLevel level, int credits) {
    this.level = level;
    this.credits = credits;
  }

  public ReviewerLevel getLevel() {
    return level;
  }

  public void setLevel(ReviewerLevel level) {
    this.level = level;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  @Override
  public String toString() {
    return "ReviewLevelCreditsPojo{" + "level=" + level + ", credits=" + credits + '}';
  }
}
