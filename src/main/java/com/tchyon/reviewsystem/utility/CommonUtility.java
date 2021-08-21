package com.tchyon.reviewsystem.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Platform review System
 *
 * @author Ranjit Prajapati
 * @version 1.0
 * @since 20-08-2021
 */
@Component
public class CommonUtility {

  /**
   * Check if string is empty or null
   *
   * @param word - value to be checked against
   * @return - false if value is empty or null
   */
  public boolean isStringValid(String word) {
    return StringUtils.trimToNull(word) == null;
  }

  /**
   * Generates map by switch key with value and vice versa
   *
   * <p>To prevent data loss make sure there is 1-1 value in map
   *
   * @param data - data to be switched
   * @return - switched map
   */
  public Map<Integer, String> switchKeyValue(Map<String, Integer> data) {
    return data.entrySet().stream()
        .collect(
            Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (v1, v2) -> v1, HashMap::new));
  }

  /**
   * Get yesterday date
   *
   * @return yesterday date
   */
  public LocalDateTime yesterdayDate() {
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime yesterday = today.minusDays(1);
    return yesterday;
  }
}
