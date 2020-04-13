package com.xyz.bit.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommUtility {

  public static String convertToUpperCase(String text) {
    return text.toUpperCase();
  }


  public static boolean isNotEmpty(String text) {
    return (text != null && !text.trim().isEmpty() ? true : false);
  }

  public static Date getCurrentDate() {
    return new java.sql.Timestamp(new java.util.Date().getTime());
  }

  public static String formatDateToString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    return sdf.format(date);
  }
}
