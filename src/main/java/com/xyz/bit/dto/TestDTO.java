package com.xyz.bit.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TestDTO {

  private int testId;
  private String testReferenceNumber;
  private int courseId;
  private String testTaken;
  private int studentId;
}
