package com.tchyon.reviewsystem.service;

import com.tchyon.reviewsystem.Exceptions.UserException;
import com.tchyon.reviewsystem.dao.UserDao;
import com.tchyon.reviewsystem.pojo.UserPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 19-08-2021
 */
@Service
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired private UserDao dao;

  public int createUser(String userName) throws UserException {
    logger.debug("UserService :: createUser :: userName : {}", userName);
    return dao.createUser(userName);
  }

  public List<UserPojo> getAllUsers() {
    logger.debug("UserService :: getAllUsers");
    return dao.getAllUsers();
  }
}
