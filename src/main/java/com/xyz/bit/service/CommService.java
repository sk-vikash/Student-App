package com.xyz.bit.service;

import com.google.gson.Gson;
import com.xyz.bit.constant.ICommConstant;
import com.xyz.bit.dao.CommDAO;
import com.xyz.bit.dto.NotificationDTO;
import com.xyz.bit.dto.QuestionDTO;
import com.xyz.bit.dto.ResponseDetails;
import com.xyz.bit.dto.ResultDTO;
import com.xyz.bit.dto.StudentDTO;
import com.xyz.bit.dto.TestDTO;
import com.xyz.bit.dto.TestResult;
import com.xyz.bit.dto.VerifyAnswerDTO;
import com.xyz.bit.exception.CommException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommService {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  @Autowired
  ResultDTO resultDTO;
  @Autowired
  private CommDAO commDAO;

  public ResponseEntity<ResponseDetails> setReourceService(StudentDTO rvcdto) throws CommException {
    LOGGER.info("Enter CommService.setReourceService() " + rvcdto);
    boolean flag = commDAO.addStudentDetailsToDB(rvcdto);
    ResponseDetails details;
    if (flag) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.STUDENT_DETAILS_ADDED);
      LOGGER.info("Exit CommService.setReourceService() ");
      return new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(206);
      details.setDetails(ICommConstant.INVALID_STUDENT_REFERENCE);
      LOGGER.info("Exit CommService.setReourceService() ");
      return new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
  }

  public ResponseEntity<ResponseDetails> setReourcePassword(StudentDTO rvcdto)
      throws CommException {
    LOGGER.info("Enter CommService.setReourcePassword() " + rvcdto);
    boolean flag = commDAO.addStudentPasswordToDB(rvcdto);
    ResponseDetails details;
    if (flag) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.STUDENT_PASSWORD_SET);
      LOGGER.info("Exit CommService.setReourcePassword() ");
      return new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(206);
      details.setDetails(ICommConstant.INVALID_STUDENT_REFERENCE);
      LOGGER.info("Exit CommService.setReourcePassword() ");
      return new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
  }

  public ResponseEntity<ResponseDetails> checkIfStudentExists(String referenceNumber)
      throws CommException {
    LOGGER.info("Enter CommService.checkIfStudentExists() " + referenceNumber);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    int checkForStudent = commDAO.checkIfStudentExistsInDB(referenceNumber);
    if (checkForStudent == 0) {
      details = new ResponseDetails();
      details.setId(204);
      details.setDetails(ICommConstant.INVALID_STUDENT_REFERENCE);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else if (checkForStudent == 1) {
      details = new ResponseDetails();
      details.setId(206);
      details.setDetails(ICommConstant.STUDENT_FOUND_MISSING_DETAIL_MISSING_PASSWORD);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else if (checkForStudent == 2) {
      details = new ResponseDetails();
      details.setId(205);
      details.setDetails(ICommConstant.STUDENT_FOUND_DETAIL_AVAILABLE_MISSING_PASSWORD);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else if (checkForStudent == 3) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(
          ICommConstant.STUDENT_FOUND_DETAILS_AVAILABLE_PASSWORD_AVAILABLE_ENTER_PASSWORD);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
    LOGGER.info("Exit CommService.checkIfStudentExists() " + referenceNumber);
    return responseEntity;

  }

  public ResponseEntity<ResponseDetails> authenticateStudent(StudentDTO rvcdto)
      throws CommException {
    LOGGER.info("Enter CommService.authenticateStudent() " + rvcdto);
    int i = commDAO.authenticateStudentCredInDB(rvcdto);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (i == 1) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.STUDENT_VALIDATED);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else if (i == 0) {
      details = new ResponseDetails();
      details.setId(204);
      details.setDetails(ICommConstant.INVALID_PASSWORD);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
    LOGGER.info("Exit CommService.authenticateStudent() " + rvcdto);
    return responseEntity;
  }

  public List<TestDTO> fetchStudentTest(String referenceNumber) throws CommException {
    LOGGER.info("Enter CommService.fetchStudentTest() " + referenceNumber);
    List<ResultDTO> listResultDTO = commDAO.fetchResultDataFromDB(referenceNumber);
    List<TestDTO> listTestDTO = commDAO.fetchStudentTestFromDB(referenceNumber);

    LOGGER.info("********************* " + listResultDTO);
    LOGGER.info("*********************  " + listTestDTO);

    if (listResultDTO.isEmpty()) {
      for (int i = 0; i < listTestDTO.size(); i++) {
        listTestDTO.get(i).setTestTaken("N");
      }
    } else {
      for (int i = 0; i < listTestDTO.size(); i++) {
        for (int j = 0; j < listResultDTO.size(); j++) {
          if ((listTestDTO.get(i).getStudentId() == listResultDTO.get(j).getStudentId())
              && (listTestDTO.get(i).getTestId() == listResultDTO.get(j).getTestId())) {
            listTestDTO.get(i).setTestTaken("Y");
            break;
          } else {
            listTestDTO.get(i).setTestTaken("N");
          }
        }
      }
    }
    LOGGER.info("Exit CommService.fetchStudentTest() " + listTestDTO);
    return listTestDTO;
  }

  public ResponseEntity<ResponseDetails> fetchStudentTestQuestion(String testReference)
      throws CommException {
    LOGGER.info("Enter CommService.fetchStudentTestQuestion() " + testReference);
    List<QuestionDTO> questionDTOSList = commDAO.fetchStudentTestQuestionFromDB(testReference);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (null != questionDTOSList && questionDTOSList.size() > 0) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(String.valueOf(questionDTOSList.size()));
      details.setList(questionDTOSList);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(500);
      details.setDetails(ICommConstant.INTERNAL_SERVER_ERROR);
      responseEntity = new ResponseEntity<ResponseDetails>(details,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    LOGGER.info("Exit CommService.fetchStudentTestQuestion() " + responseEntity);
    return responseEntity;
  }

  public ResponseEntity<ResponseDetails> saveTestResults(TestResult testResult)
      throws CommException {
    LOGGER.info("Enter CommService.saveTestResult() " + testResult);
    List<VerifyAnswerDTO> verifyAnswerDTOList = commDAO
        .fetchAnswerForTestFromDB(testResult.getTestReferenceNumber());
    LOGGER.info("****** verifyAnswerDTOList " + verifyAnswerDTOList);
    int correctAnswer = 0;
    int inCorrectAnswer = 0;
    int totalQuestion = 0;
    Gson g = new Gson();
    QuestionDTO[] questionDTOS = g.fromJson(testResult.getQuestion(), QuestionDTO[].class);
    for (int i = 0; i < questionDTOS.length; i++) {
      if (questionDTOS[i].getQuestionId() == verifyAnswerDTOList.get(i).getQuestionId()) {
        if (questionDTOS[i].getMarkedAnswer() == verifyAnswerDTOList.get(i).getCorrectOption()) {
          correctAnswer = correctAnswer + 1;
        } else {
          inCorrectAnswer = inCorrectAnswer + 1;
        }
        totalQuestion = totalQuestion + 1;
      }
    }
    resultDTO.setStudentReferenceNumber(testResult.getStudentReferenceNumber());
    resultDTO.setTestReferenceNumber(testResult.getTestReferenceNumber());
    resultDTO.setRegistered("Y");
    resultDTO.setTestTaken("Y");
    resultDTO.setScore(String.valueOf(correctAnswer));
    resultDTO.setTestQuestion(totalQuestion);
    resultDTO.setCorrectAnswer(correctAnswer);
    resultDTO.setInCorrectAnswer(inCorrectAnswer);
    boolean flag = commDAO.saveTestResult(resultDTO);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (flag) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.RESULT_SAVED);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(500);
      details.setDetails(ICommConstant.INTERNAL_SERVER_ERROR);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
    LOGGER.info("Exit CommService.saveTestResult() " + resultDTO);
    return responseEntity;
  }

  public ResponseEntity<ResponseDetails> saveTestResults_1(ResultDTO resultDTO)
      throws CommException {
    LOGGER.info("Enter CommService.saveTestResult() " + resultDTO);
    boolean flag = commDAO.saveTestResult(resultDTO);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (flag) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.RESULT_SAVED);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(500);
      details.setDetails(ICommConstant.INTERNAL_SERVER_ERROR);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    }
    LOGGER.info("Exit CommService.saveTestResult() " + resultDTO);
    return responseEntity;
  }

  public ResponseEntity<ResponseDetails> fetchTestResult(ResultDTO resultDTO) throws CommException {
    LOGGER.info("Enter CommService.fetchTestResult() " + resultDTO);
    List<ResultDTO> resultDTOList = commDAO.getTestResult(resultDTO);
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (null != resultDTOList && resultDTOList.size() > 0) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.RESULT_FOUND);
      details.setList(resultDTOList);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(500);
      details.setDetails(ICommConstant.INTERNAL_SERVER_ERROR);
      responseEntity = new ResponseEntity<ResponseDetails>(details,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    LOGGER.info("Exit CommService.fetchTestResult() " + responseEntity);
    return responseEntity;
  }

  public ResponseEntity<ResponseDetails> fetchNotification() throws CommException {
    LOGGER.info("Enter CommService.fetchNotification() ");
    List<NotificationDTO> notificationDTOS = commDAO.getNotification();
    ResponseDetails details;
    ResponseEntity<ResponseDetails> responseEntity = null;
    if (null != notificationDTOS && notificationDTOS.size() > 0) {
      details = new ResponseDetails();
      details.setId(200);
      details.setDetails(ICommConstant.RESULT_FOUND);
      details.setList(notificationDTOS);
      responseEntity = new ResponseEntity<ResponseDetails>(details, HttpStatus.OK);
    } else {
      details = new ResponseDetails();
      details.setId(500);
      details.setDetails(ICommConstant.INTERNAL_SERVER_ERROR);
      responseEntity = new ResponseEntity<ResponseDetails>(details,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    LOGGER.info("Exit CommService.fetchNotification() " + responseEntity);
    return responseEntity;
  }
}
