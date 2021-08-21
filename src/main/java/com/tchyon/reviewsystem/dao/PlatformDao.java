package com.tchyon.reviewsystem.dao;

import com.tchyon.reviewsystem.Exceptions.PlatformException;
import com.tchyon.reviewsystem.pojo.*;
import com.tchyon.reviewsystem.utility.ActingDBUtility;
import com.tchyon.reviewsystem.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 20-08-2021
 */
@Repository
public class PlatformDao {

  private final Logger logger = LoggerFactory.getLogger(PlatformDao.class);

  @Autowired private ActingDBUtility utility;

  @Autowired private CommonUtility commonUtility;

  private int platformGeneratorIndex = 0;
  private int verticalGeneratorIndex = 0;

  public int createPlatform(String platformName, String verticalName, LocalDateTime releaseDate)
      throws PlatformException {
    Map<String, Integer> platformIdMap = utility.getPlatformIdMap();
    if (platformIdMap.containsKey(platformName)) {
      throw new PlatformException(String.format("%s platform already exists", platformName));
    }

    Map<String, Integer> verticalIdMap = utility.getVerticalIdMap();
    if (!verticalIdMap.containsKey(verticalName)) {
      logger.debug(
          "PlatformDao :: createPlatform : '{}' vertical not exists. Creating the vertical '{}'",
          verticalName,
          verticalName);
      this.addVertical(verticalName);
    }
    int verticalId = verticalIdMap.get(verticalName);
    return this.addPlatform(platformName, verticalId, releaseDate);
  }

  public int addVertical(String verticalName) {
    int currentId = ++verticalGeneratorIndex;
    Map<Integer, VerticalPojo> verticalList = utility.getVerticalMapList();
    VerticalPojo vertical = new VerticalPojo(currentId, verticalName);
    verticalList.put(verticalGeneratorIndex, vertical);
    this.verticalIndex(verticalGeneratorIndex, verticalName);
    logger.debug(
        "PlatformDao :: addVertical : '{}' vertical Created Successfully with id : '{}'",
        verticalName,
        currentId);
    return currentId;
  }

  public boolean verticalIndex(int verticalId, String verticalName) {
    Map<String, Integer> verticalIdMap = utility.getVerticalIdMap();
    verticalIdMap.put(verticalName, verticalId);
    logger.debug("PlatformDao :: verticalIndex : '{}' vertical index created", verticalName);
    return true;
  }

  public int addPlatform(String platformName, int verticalId, LocalDateTime releaseDate) {
    int currentId = ++platformGeneratorIndex;
    LocalDateTime date = releaseDate == null ? commonUtility.yesterdayDate() : releaseDate;
    Map<Integer, PlatformPojo> platformList = utility.getPlatformMapList();
    PlatformPojo platform = new PlatformPojo(currentId, verticalId, platformName, date);
    platformList.put(platformGeneratorIndex, platform);
    this.platformIndex(platformGeneratorIndex, platformName);
    this.platformVerticalMap(platformGeneratorIndex, verticalId);
    logger.debug(
        "PlatformDao :: addPlatform : '{}' platform created successfully with id : '{}'",
        platformName,
        currentId);
    return currentId;
  }

  public boolean platformIndex(int platformId, String platformName) {
    Map<String, Integer> platformIdMap = utility.getPlatformIdMap();
    platformIdMap.put(platformName, platformId);
    logger.debug(
        "PlatformDao :: platformIndex : '{}' platform index created successfully", platformName);
    return true;
  }

  public boolean platformVerticalMap(int platformId, int verticalId) {
    Map<Integer, Integer> platformVerticalMap = utility.getPlatformVerticalMap();
    platformVerticalMap.put(platformId, verticalId);
    logger.debug(
        "PlatformDao :: platformVerticalMap : '{}' platform id mapped successfully to vertical id : '{}'",
        platformId,
        verticalId);
    return true;
  }

  public List<ViewPlatformPojo> showPlatforms() {
    Map<Integer, Integer> platformVerticalMap = utility.getPlatformVerticalMap();
    Map<Integer, PlatformPojo> platformList = utility.getPlatformMapList();
    Map<Integer, VerticalPojo> verticalList = utility.getVerticalMapList();
    List<ViewPlatformPojo> platforms = new ArrayList<>();

    platformVerticalMap.forEach(
        (platformId, verticalId) -> {
          VerticalPojo verticalPojo = verticalList.get(verticalId);
          PlatformPojo platformPojo = platformList.get(platformId);

          ViewPlatformPojo platform =
              new ViewPlatformPojo(
                  platformPojo.getPlatformName(),
                  verticalPojo.getVerticalName(),
                  platformId,
                  verticalId,
                  platformPojo.getReleaseDate());
          platforms.add(platform);
        });

    return platforms;
  }

  public PlatformPojo getPlatformByName(String platformName) throws PlatformException {
    Map<String, Integer> platformIdMap = utility.getPlatformIdMap();
    if (!platformIdMap.containsKey(platformName)) {
      throw new PlatformException(String.format("%s platform does not exists", platformName));
    }
    Map<Integer, PlatformPojo> platformList = utility.getPlatformMapList();
    logger.debug(
        "PlatformDao :: getPlatformByName : platform found with name : '{}' ", platformName);
    return platformList.get(platformIdMap.get(platformName));
  }

  public Map<String, Double> averagePlatformScore() {
    Map<Integer, PlatformPojo> platformList = utility.getPlatformMapList();
    Map<Integer, ReviewPojo> reviewList = utility.getReviewMapList();
    Map<Integer, Double> averageOfReviewPlatform =
        reviewList.values().stream()
            .collect(
                Collectors.groupingBy(
                    row -> row.getPlatformId(), Collectors.averagingInt(row -> row.getReview())));

    return platformList.values().stream()
        .collect(
            Collectors.toMap(
                row -> row.getPlatformName(),
                row -> averageOfReviewPlatform.getOrDefault(row.getPlatformId(), 0.0)));
  }

  public Map<String, Integer> topNReviewTotalByCriticsInVertical(int n, String verticalName) {
    Map<Integer, Integer> platformVerticalMap = utility.getPlatformVerticalMap();
    Map<Integer, ReviewPojo> reviewList = utility.getReviewMapList();
    Map<String, Integer> verticalIdMap = utility.getVerticalIdMap();
    Map<String, Integer> platformIdMap = utility.getPlatformIdMap();
    Map<Integer, String> idPlatformMap = commonUtility.switchKeyValue(platformIdMap);

    int verticalId = verticalIdMap.get(verticalName);

    List<Integer> platformIds =
        platformVerticalMap.entrySet().stream()
            .filter(entry -> entry.getKey() == verticalId)
            .map(entry -> entry.getValue())
            .limit(n)
            .collect(Collectors.toList());

    return reviewList.values().stream()
        .filter(
            row ->
                platformIds.contains(row.getPlatformId())
                    && row.getReviewerLevel().equals(ReviewerLevel.CRITIC))
        .collect(
            Collectors.groupingBy(
                row -> idPlatformMap.get(row.getPlatformId()),
                Collectors.summingInt(row -> row.getReview())));
  }
}
