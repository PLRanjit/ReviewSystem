package com.tchyon.reviewsystem;

import com.tchyon.reviewsystem.controller.Controller;
import com.tchyon.reviewsystem.pojo.UserPojo;
import com.tchyon.reviewsystem.pojo.ViewPlatformPojo;
import com.tchyon.reviewsystem.pojo.ViewReviewPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ReviewSystemApplication implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(ReviewSystemApplication.class);

  @Autowired private Controller controller;

  public static void main(String[] args) {
    SpringApplication.run(ReviewSystemApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    String releaseDate1 = "25/08/2021 12:00";
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse(releaseDate1, format);

    // Add Platform
    controller.addPlatform("Wint Wealth", "Alternative Investment");
    controller.addPlatform("BuyUcoin", "Cryptocurreny");
    controller.addPlatform("5paisa", "Equity");
    controller.addPlatform("Grip", "Alternative Investment");

    // platform with no review
    controller.addPlatform("Wazirx", "Alternative Investment");

    // platform with release date
    controller.addPlatform("UpStox", "Alternative Investment", date);

    // Add User
    controller.addUser("Adam");
    controller.addUser("Patrick");
    controller.addUser("Trump");

    // Add Review User upgrade
    controller.addReview("Adam", "5paisa", 2);
    controller.addReview("Adam", "Wint Wealth", 3);
    controller.addReview("Adam", "Grip", 5);

    // Multiple Review Exception
    //    controller.addReview("Adam", "Grip", 5);

    // Early release day exception
    //    controller.addReview("Adam", "UpStox", 5);

    controller.addReview("Trump", "5paisa", 4);
    controller.addReview("Trump", "BuyUcoin", 1);
    controller.addReview("Trump", "Grip", 5);

    // Top N platform of Vertical of Critics level viewer
    controller.addReview("Adam", "BuyUcoin", 2);

    Map<String, Double> averagePlatform = controller.averagePlatformScore();
    logger.info("Platform Wise Average :: {}", averagePlatform);

    int limit = 5;
    Map<String, Integer> platformTotalScore =
        controller.topNReviewTotalByCriticsInVertical(limit, "Cryptocurreny");
    logger.info(
        "Top {} Platform of vertical with Critics level User review :: {} ",
        limit,
        platformTotalScore);

    List<ViewReviewPojo> review = controller.getViewReview();
    logger.debug("Reviews :: Size : {} Reviews : {}", review.size(), review);

    List<ViewPlatformPojo> platformInfo = controller.showAllPlatform();
    logger.debug("Platforms :: Size : {} Platforms : {}", platformInfo.size(), platformInfo);

    List<UserPojo> userList = controller.getAllUsers();
    logger.debug("Users :: Size : {} Users : {}", userList.size(), userList);
  }
}
