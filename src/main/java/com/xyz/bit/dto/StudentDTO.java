package com.xyz.bit.dto;

import com.xyz.bit.utility.CommUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentDTO {

  private String studentReferenceNumber;
  private String studentPassword;
  private String studentName;
  private String studentAddress;
  private String studentGender;
  private String studentEmail;
  private String studentQualification;
  private long studentMobile;
  private String testReferenceNumber;

  public String getStudentReferenceNumber() {
    String tempStudentReferenceNumber = null;
    if (CommUtility.isNotEmpty(studentReferenceNumber)) {
      tempStudentReferenceNumber = CommUtility.convertToUpperCase(studentReferenceNumber);
    }
    return tempStudentReferenceNumber;
  }

  public String getStudentEmail() {
    String tempStudentEmail = null;
    if (CommUtility.isNotEmpty(studentEmail)) {
      tempStudentEmail = CommUtility.convertToUpperCase(studentEmail);
    }
    return tempStudentEmail;
  }

  public String getStudentQualification() {
    String tempStudentQualification = null;
    if (CommUtility.isNotEmpty(studentQualification)) {
      tempStudentQualification = CommUtility.convertToUpperCase(studentQualification);
    }
    return tempStudentQualification;
  }

  public String getStudentAddress() {
    String tempStudentAddress = null;
    if (CommUtility.isNotEmpty(studentAddress)) {
      tempStudentAddress = CommUtility.convertToUpperCase(studentAddress);
    }
    return tempStudentAddress;
  }

  public String getStudentName() {
    String tempStudentName = null;
    if (CommUtility.isNotEmpty(studentName)) {
      tempStudentName = CommUtility.convertToUpperCase(studentName);
    }
    return tempStudentName;
  }
}
