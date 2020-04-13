package com.xyz.bit.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NotificationDTO {

  private String message;
  private String startDate;
  private String endDate;
  private String priority;
}
