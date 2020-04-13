package com.xyz.bit.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VerifyAnswerDTO {

  private int questionId;
  //private int questionSerialNumber;
  //private String question;
  //private String optionOne;
  //private int optionOneValue;
  //private String optionTwo;
  //private int optionTwoValue;
  //private String optionThree;
  //private int optionThreeValue;
  //private String optionFour;
  //private int optionFourValue;
  private int correctOption;
  //private String subject;
  //private int markedAnswer;
}
