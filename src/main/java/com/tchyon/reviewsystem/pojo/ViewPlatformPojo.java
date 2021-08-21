package com.tchyon.reviewsystem.pojo;

import java.time.LocalDateTime;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 21-08-2021
 */
public class ViewPlatformPojo {
  private String platformName;
  private String verticalName;
  private int platformId;
  private int verticalId;
  private LocalDateTime platformReleaseDate;

  public ViewPlatformPojo() {}

  public ViewPlatformPojo(
      String platformName,
      String verticalName,
      int platformId,
      int verticalId,
      LocalDateTime platformReleaseDate) {
    this.platformName = platformName;
    this.verticalName = verticalName;
    this.platformId = platformId;
    this.verticalId = verticalId;
    this.platformReleaseDate = platformReleaseDate;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public String getVerticalName() {
    return verticalName;
  }

  public void setVerticalName(String verticalName) {
    this.verticalName = verticalName;
  }

  public int getPlatformId() {
    return platformId;
  }

  public void setPlatformId(int platformId) {
    this.platformId = platformId;
  }

  public int getVerticalId() {
    return verticalId;
  }

  public void setVerticalId(int verticalId) {
    this.verticalId = verticalId;
  }

  public LocalDateTime getPlatformReleaseDate() {
    return platformReleaseDate;
  }

  public void setPlatformReleaseDate(LocalDateTime platformReleaseDate) {
    this.platformReleaseDate = platformReleaseDate;
  }

  @Override
  public String toString() {
    return "\nViewPlatformPojo{"
        + "platformName='"
        + platformName
        + '\''
        + ", verticalName='"
        + verticalName
        + '\''
        + ", platformId="
        + platformId
        + ", verticalId="
        + verticalId
        + ", platfromReleaseDate="
        + platformReleaseDate
        + '}';
  }
}
