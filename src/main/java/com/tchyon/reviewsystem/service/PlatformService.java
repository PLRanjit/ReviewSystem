package com.tchyon.reviewsystem.service;

import com.tchyon.reviewsystem.dao.PlatformDao;
import com.tchyon.reviewsystem.exceptions.PlatformException;
import com.tchyon.reviewsystem.pojo.ViewPlatformPojo;
import com.tchyon.reviewsystem.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 20-08-2021
 */
@Service
public class PlatformService {

  private final Logger logger = LoggerFactory.getLogger(PlatformService.class);

  @Autowired private PlatformDao dao;
  @Autowired private CommonUtility commonUtility;

  public int addPlatform(String platformName, String verticalName, LocalDateTime releaseDate)
      throws PlatformException {
    logger.debug(
        "PlatformService :: addPlatform :: platformName : {}, verticalName : {}, releaseDate : {}",
        platformName,
        verticalName,
        releaseDate);
    if (commonUtility.isStringValid(platformName) || commonUtility.isStringValid(verticalName)) {
      throw new PlatformException("platform name and vertical name can't be null or empty");
    }
    return dao.createPlatform(platformName, verticalName, releaseDate);
  }

  public List<ViewPlatformPojo> showPlatforms() {
    logger.debug("PlatformService :: showPlatforms");
    return dao.showPlatforms();
  }

  public Map<String, Double> averagePlatformScore() {
    logger.debug("PlatformService :: averagePlatformScore");
    return dao.averagePlatformScore();
  }

  public Map<String, Integer> topNReviewTotalByCriticsInVertical(int n, String verticalName) {
    logger.debug(
        "PlatformService :: topNReviewTotalByCriticsInVertical :: n : {}, verticalName : {}",
        n,
        verticalName);
    return dao.topNReviewTotalByCriticsInVertical(n, verticalName);
  }
}
