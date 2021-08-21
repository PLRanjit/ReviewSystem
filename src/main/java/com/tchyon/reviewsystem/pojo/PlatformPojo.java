package com.tchyon.reviewsystem.pojo;

import java.time.LocalDateTime;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
public class PlatformPojo {
  private int platformId;
  private int verticalId;
  private String platformName;
  private LocalDateTime releaseDate;

  public PlatformPojo() {}

  public PlatformPojo(
      int platformId, int verticalId, String platformName, LocalDateTime releaseDate) {
    this.platformId = platformId;
    this.verticalId = verticalId;
    this.platformName = platformName;
    this.releaseDate = releaseDate;
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

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public LocalDateTime getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDateTime releaseDate) {
    this.releaseDate = releaseDate;
  }

  @Override
  public String toString() {
    return "PlatformPojo{"
        + "platformId="
        + platformId
        + ", verticalId="
        + verticalId
        + ", platformName='"
        + platformName
        + '\''
        + ", releaseDate="
        + releaseDate
        + '}';
  }
}
