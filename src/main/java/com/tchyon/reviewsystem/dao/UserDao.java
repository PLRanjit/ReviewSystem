package com.tchyon.reviewsystem.dao;

import com.tchyon.reviewsystem.exceptions.UserException;
import com.tchyon.reviewsystem.pojo.ReviewerLevel;
import com.tchyon.reviewsystem.pojo.UserPojo;
import com.tchyon.reviewsystem.utility.ActingDBUtility;
import com.tchyon.reviewsystem.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
@Repository
public class UserDao {

  private final Logger logger = LoggerFactory.getLogger(UserDao.class);

  @Autowired private ActingDBUtility utility;

  @Autowired private CommonUtility commonUtility;

  private int userGeneratorIndex = 0;

  /**
   * Create the user return it's generated id
   *
   * @param userName - unique name of the reviewer
   * @return - Generated id
   * @throws UserException - throw exception if username not present or already exists
   */
  public int createUser(String userName) throws UserException {
    if (commonUtility.isStringValid(userName)) {
      throw new UserException("user name can't be null or empty");
    }
    Map<String, Integer> userIdMap = utility.getUserIdMap();
    if (userIdMap.containsKey(userName)) {
      throw new UserException(String.format("'%s'User already exists ", userName));
    }
    return this.addUser(userName);
  }

  public int addUser(String userName) {
    int currentId = ++userGeneratorIndex;
    Map<Integer, UserPojo> userList = utility.getUserMapList();
    UserPojo user =
        new UserPojo(currentId, userName, ReviewerLevel.VIEWER, LocalDateTime.now(), null);
    userList.put(currentId, user);
    this.userIndex(currentId, userName);
    logger.debug(
        "UserDao :: addUser : '{}' user created successfully with id : '{}'", userName, currentId);
    return currentId;
  }

  public boolean userIndex(int userId, String userName) {
    Map<String, Integer> userIdMap = utility.getUserIdMap();
    userIdMap.put(userName, userId);
    logger.debug("UserDao :: userIndex : '{}' user index created successfully", userName);
    return true;
  }

  public UserPojo getUserByUserName(String userName) throws UserException {
    Map<String, Integer> userIdMap = utility.getUserIdMap();
    if (!userIdMap.containsKey(userName)) {
      throw new UserException(String.format("%s user not found", userName));
    }
    logger.debug("UserDao :: getUserByUserName : user found with name : '{}' ", userName);
    return utility.getUserMapList().get(userIdMap.get(userName));
  }

  public boolean upgradeUser(int userId, int threshold, ReviewerLevel level) throws UserException {
    Map<Integer, UserPojo> userList = utility.getUserMapList();
    if (!userList.containsKey(userId)) {
      throw new UserException(String.format("%s user do not exists", userId));
    }
    UserPojo userPojo = userList.get(userId);
    userPojo.setLevel(level);
    userPojo.setUpdatedOn(LocalDateTime.now());
    logger.info(
        "UserDao :: upgradeUser : Since user '{}' has published '{}' review. he/she is promoted to '{}' level now",
        userPojo.getUserName(),
        threshold,
        level);
    return true;
  }

  public List<UserPojo> getAllUsers() {
    Map<Integer, UserPojo> userList = utility.getUserMapList();
    return userList.values().stream().collect(Collectors.toList());
  }
}
