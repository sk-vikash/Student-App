package com.xyz.bit.dao;

import com.xyz.bit.config.CommDBConfig;
import com.xyz.bit.dto.NotificationDTO;
import com.xyz.bit.dto.QuestionDTO;
import com.xyz.bit.dto.ResultDTO;
import com.xyz.bit.dto.StudentDTO;
import com.xyz.bit.dto.TestDTO;
import com.xyz.bit.dto.VerifyAnswerDTO;
import com.xyz.bit.exception.CommException;
import com.xyz.bit.mapper.NotificationRowMapper;
import com.xyz.bit.mapper.QuestionRowMapper;
import com.xyz.bit.mapper.ResultDataRowMapper;
import com.xyz.bit.mapper.ResultRowMapper;
import com.xyz.bit.mapper.TestRowMapper;
import com.xyz.bit.mapper.VerifyAnswerRowMapper;
import com.xyz.bit.query.IQuery;
import com.xyz.bit.utility.CommUtility;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service

@Repository
public class CommDAO {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  @Autowired
  CommDBConfig commDBConfig;

  public boolean addStudentDetailsToDB(StudentDTO rvcdto) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.addResourceToDB():" + rvcdto);
      boolean flag = false;
      Object[] args = new Object[7];
      args[0] = rvcdto.getStudentName();
      args[1] = rvcdto.getStudentAddress();
      args[2] = rvcdto.getStudentGender();
      args[3] = rvcdto.getStudentEmail();
      args[4] = rvcdto.getStudentQualification();
      args[5] = rvcdto.getStudentMobile();
      args[6] = rvcdto.getStudentReferenceNumber();
      int i = commDBConfig.getJdbcTemplate().update(IQuery.UPDATE_RESOURCE_DETAILS, args);
      if (i > 0) {
        flag = true;
      }
      LOGGER.info("Exit CommDAO.addResourceToDB():" + flag);
      return flag;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.addResourceToDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public boolean addStudentPasswordToDB(StudentDTO rvcdto) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.addResourcePasswordToDB():" + rvcdto);
      boolean flag = false;
      Object[] args = new Object[3];
      args[0] = rvcdto.getStudentReferenceNumber();
      args[1] = rvcdto.getStudentReferenceNumber();
      args[2] = rvcdto.getStudentPassword();

      int i = commDBConfig.getJdbcTemplate().update(IQuery.ADD_RESOURCE_PASSWORD, args);
      if (i > 0) {
        flag = true;
      }
      LOGGER.info("Exit CommDAO.addResourcePasswordToDB():" + flag);
      return flag;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.addResourcePasswordToDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public int checkIfStudentExistsInDB(String referenceNumber) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.checkIfReourceExistsInDB():" + referenceNumber);
      int k = -99;
      String studentName = null;
      String studentPassword = null;
      Object[] args_student = new Object[1];
      args_student[0] = referenceNumber;
      int i = commDBConfig.getJdbcTemplate()
          .queryForObject(IQuery.CHECK_RESOURCE_IN_STUDENT, args_student, Integer.class);
      if (i == 0) {
        k = 0;
      } else {
        studentName = commDBConfig.getJdbcTemplate()
            .queryForObject(IQuery.CHECK_STUDENT_NAME_IN_STUDENT, args_student, String.class);
        int j = commDBConfig.getJdbcTemplate()
            .queryForObject(IQuery.CHECK_RESOURCE_IN_CREDENTIAL, args_student, Integer.class);
        if (studentName == null && j == 0) {
          k = 1;
        } else if (studentName != null && j == 0) {
          k = 2;
        } else if (studentName != null && j == 1) {
          k = 3;
        }
      }
      return k;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.checkIfReourceExistsInDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public int authenticateStudentCredInDB(StudentDTO rvcdto) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.authenticateStudentInDB():" + rvcdto);
      Object[] args = new Object[2];
      args[0] = rvcdto.getStudentReferenceNumber();
      args[1] = rvcdto.getStudentPassword();
      int i = commDBConfig.getJdbcTemplate()
          .queryForObject(IQuery.VALIDATE_RESOURCE_IN_CREDENTIAL, args, Integer.class);
      LOGGER.info("Exit CommDAO.authenticateStudentInDB(): " + i);
      return i;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.authenticateStudentInDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public List<TestDTO> fetchStudentTestFromDB(String referenceNumber) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.fetchStudentTestFromDB():" + referenceNumber);
      List<TestDTO> dtos = null;
      Object[] args = new Object[1];
      args[0] = referenceNumber;
      dtos = commDBConfig.getJdbcTemplate()
          .query(IQuery.FETCH_STUDENT_TEST, args, new TestRowMapper());
      LOGGER.info("Exit CommDAO.fetchStudentTestFromDB(): " + dtos);
      return dtos;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.fetchStudentTestFromDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public List<ResultDTO> fetchResultDataFromDB(String referenceNumber) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.fetchResultDataFromDB():" + referenceNumber);
      List<ResultDTO> dtos = null;
      Object[] args = new Object[1];
      args[0] = referenceNumber;
      dtos = commDBConfig.getJdbcTemplate()
          .query(IQuery.FETCH_RESULT_DATA, args, new ResultDataRowMapper());
      LOGGER.info("Exit CommDAO.fetchResultDataFromDB(): " + dtos);
      return dtos;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.fetchResultDataFromDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public List<QuestionDTO> fetchStudentTestQuestionFromDB(String testReference)
      throws CommException {
    try {
      LOGGER.info("Enter CommDAO.fetchStudentTestQuestionFromDB():" + testReference);
      List<QuestionDTO> qtos = null;
      Object[] args = new Object[1];
      args[0] = testReference;
      qtos = commDBConfig.getJdbcTemplate()
          .query(IQuery.FETCH_STUDENT_TEST_QUESTION, args, new QuestionRowMapper());
      LOGGER.info("Exit CommDAO.fetchStudentTestQuestionFromDB(): " + qtos);
      return qtos;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.fetchStudentTestQuestionFromDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }


  public List<VerifyAnswerDTO> fetchAnswerForTestFromDB(String testReference) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.fetchAnswerForTestFromDB():" + testReference);
      List<VerifyAnswerDTO> verifyAnswerDTOList = null;
      Object[] args = new Object[1];
      args[0] = testReference;
      verifyAnswerDTOList = commDBConfig.getJdbcTemplate()
          .query(IQuery.FETCH_STUDENT_TEST_ANSWER, args, new VerifyAnswerRowMapper());
      LOGGER.info("Exit CommDAO.fetchAnswerForTestFromDB(): " + verifyAnswerDTOList);
      return verifyAnswerDTOList;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.fetchAnswerForTestFromDB: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }
  }

  public boolean saveTestResult(ResultDTO resultDTO) throws CommException {
    LOGGER.info("Enter CommDAO.saveTestResult():" + resultDTO);
    try {
      boolean flag = false;
      Object[] args = new Object[9];
      args[0] = resultDTO.getStudentReferenceNumber();
      args[1] = resultDTO.getTestReferenceNumber();
      args[2] = resultDTO.getRegistered();
      args[3] = resultDTO.getTestTaken();
      args[4] = CommUtility.getCurrentDate();
      args[5] = resultDTO.getScore();
      args[6] = resultDTO.getTestQuestion();
      args[7] = resultDTO.getCorrectAnswer();
      args[8] = resultDTO.getInCorrectAnswer();

      int i = commDBConfig.getJdbcTemplate().update(IQuery.SAVE_RESULT, args);
      if (i > 0) {
        flag = true;
      }
      LOGGER.info("Exit CommDAO.saveTestResult():" + flag);
      return flag;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.saveTestResult: " + e);
      e.printStackTrace();
      throw new CommException("Exception while saving the result", e);
    }

  }

  public List<ResultDTO> getTestResult(ResultDTO resultDTO) throws CommException {
    try {
      LOGGER.info("Enter CommDAO.getTestResult():" + resultDTO);
      List<ResultDTO> resultDTOSList = null;
      Object[] args = new Object[4];
      args[0] = resultDTO.getStudentReferenceNumber();
      args[1] = resultDTO.getTestReferenceNumber();
      args[2] = resultDTO.getStudentReferenceNumber();
      args[3] = resultDTO.getTestReferenceNumber();
      resultDTOSList = commDBConfig.getJdbcTemplate()
          .query(IQuery.GET_RESULT, args, new ResultRowMapper());
      LOGGER.info("Exit CommDAO.getTestResult():" + resultDTOSList);
      return resultDTOSList;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.getTestResult: " + e);
      e.printStackTrace();
      throw new CommException("Exception while getting the result", e);
    }
  }

  public List<NotificationDTO> getNotification() throws CommException {
    try {
      LOGGER.info("Enter CommDAO.getNotification():");
      List<NotificationDTO> notificationDTOS = null;
//      Object[] args = new Object[4];
//      notificationDTOS = commDBConfig.getJdbcTemplate()
//          .query(IQuery.GET_NOTIFICATION, new NotificationRowMapper());

      notificationDTOS = new ArrayList<NotificationDTO>();
      NotificationDTO notificationDTO = new NotificationDTO();
      notificationDTO.setMessage("test");
      notificationDTO.setStartDate("01-01-2019");
      notificationDTO.setEndDate("01-01-2020");
      notificationDTO.setPriority("high");
      notificationDTOS.add(notificationDTO);
      LOGGER.info("Exit CommDAO.getNotification():" + notificationDTOS);
      return notificationDTOS;
    } catch (RuntimeException e) {
      LOGGER.error("Error CommDAO.getNotification: " + e);
      e.printStackTrace();
      throw new CommException("Exception while getting the notification details", e);
    }
  }
}
