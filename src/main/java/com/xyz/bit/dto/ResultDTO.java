package com.xyz.bit.dto;

import com.xyz.bit.utility.CommUtility;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@Service
public class ResultDTO {

  private int resultId;
  private int studentId;
  private int testId;
  private String studentReferenceNumber;
  private String testReferenceNumber;
  private String registered;
  private String testTaken;
  private Date testTakenDate;
  private String score;
  private int testQuestion;
  private int correctAnswer;
  private int inCorrectAnswer;
  private String formattedTestTakenDate;

  public String getStudentReferenceNumber() {
    String tempStudentReferenceNumber = null;
    if (CommUtility.isNotEmpty(studentReferenceNumber)) {
      tempStudentReferenceNumber = CommUtility.convertToUpperCase(studentReferenceNumber);
    }
    return tempStudentReferenceNumber;
  }

  public String getTestReferenceNumber() {
    String tempTestReferenceNumber = null;
    if (CommUtility.isNotEmpty(testReferenceNumber)) {
      tempTestReferenceNumber = CommUtility.convertToUpperCase(testReferenceNumber);
    }
    return tempTestReferenceNumber;
  }
}
