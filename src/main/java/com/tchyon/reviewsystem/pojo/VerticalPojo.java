package com.tchyon.reviewsystem.pojo;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
public class VerticalPojo {

  private int verticalId;
  private String verticalName;

  public VerticalPojo() {}

  public VerticalPojo(int verticalId, String verticalName) {
    this.verticalId = verticalId;
    this.verticalName = verticalName;
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

  @Override
  public String toString() {
    return "VerticalPojo{"
        + "verticalId="
        + verticalId
        + ", verticalName='"
        + verticalName
        + '\''
        + '}';
  }
}
