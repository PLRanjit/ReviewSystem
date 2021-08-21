package com.tchyon.reviewsystem.pojo;

import java.time.LocalDateTime;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
public class UserPojo {

  ReviewerLevel level;
  private int userId;
  private String userName;
  private LocalDateTime creadtedOn;
  private LocalDateTime updatedOn;

  public UserPojo() {}

  public UserPojo(
      int userId,
      String userName,
      ReviewerLevel level,
      LocalDateTime creadtedOn,
      LocalDateTime updatedOn) {
    this.userId = userId;
    this.userName = userName;
    this.level = level;
    this.creadtedOn = creadtedOn;
    this.updatedOn = updatedOn;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ReviewerLevel getLevel() {
    return level;
  }

  public void setLevel(ReviewerLevel level) {
    this.level = level;
  }

  public LocalDateTime getCreadtedOn() {
    return creadtedOn;
  }

  public void setCreadtedOn(LocalDateTime creadtedOn) {
    this.creadtedOn = creadtedOn;
  }

  public LocalDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public String toString() {
    return "\nUserPojo{"
        + "userId="
        + userId
        + ", userName='"
        + userName
        + '\''
        + ", level="
        + level
        + ", creadtedOn="
        + creadtedOn
        + ", updatedOn="
        + updatedOn
        + '}';
  }
}
