package com.xyz.bit.controller;

import com.xyz.bit.dto.ResponseDetails;
import com.xyz.bit.dto.ResultDTO;
import com.xyz.bit.dto.StudentDTO;
import com.xyz.bit.dto.TestDTO;
import com.xyz.bit.dto.TestResult;
import com.xyz.bit.exception.CommException;
import com.xyz.bit.service.CommService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dsi")
@CrossOrigin
public class CommController {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CommService commService;

  /**
   * This resource is used to checking for the student, if they exists in the DB
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/check-studentinformation/{referenceNumber}", method = RequestMethod.GET)
  public ResponseEntity<ResponseDetails> checkIfStudentExists(@PathVariable String referenceNumber)
      throws CommException {
    // ResponseEntity<ResponseDetails> responseEntity;
    LOGGER.info("Enter CommController.checkIfStudentExists() " + referenceNumber);
    return commService.checkIfStudentExists(referenceNumber.toUpperCase());
  }

  /**
   * This reource is used to add the student information in the DB. This also gives call the service
   * layer method setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/add-student-information", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> addStudentDetails(@RequestBody StudentDTO resourceDTO)
      throws CommException {
    LOGGER.info("Enter CommController.addStudentDetails() " + resourceDTO);
    return commService.setReourceService(resourceDTO);
  }


  /**
   * This reource is used to add the student password in the DB. This also gives call the service
   * layer method addResourcePassword().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/set-student-password", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> addResourcePassword(@RequestBody StudentDTO resourceDTO)
      throws CommException {
    LOGGER.info("Enter CommController.addResourcePassword() " + resourceDTO);
    return commService.setReourcePassword(resourceDTO);
  }

  /**
   * This reource is used to authenticate the student's referenec number and entered passowrd in the
   * DB. This also gives call the service layer method setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/authenticate-student", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> authenticateStudent(@RequestBody StudentDTO resourceDTO)
      throws CommException {
    LOGGER.info("Enter CommController.authenticateStudent() " + resourceDTO);
    return commService.authenticateStudent(resourceDTO);
  }

  /**
   * This reource is used to fetch student's test. This also gives call the service layer method
   * setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/fetch-student-test/{referenceNumber}", method = RequestMethod.GET)
  public List<TestDTO> fetchStudentTest(@PathVariable String referenceNumber) throws CommException {
    LOGGER.info("Enter CommController.fetchStudentTest() " + referenceNumber);
    return commService.fetchStudentTest(referenceNumber.toUpperCase());
  }

  /**
   * This reource is used to fetch student's question. This also gives call the service layer method
   * setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/fetch-question/{testReference}", method = RequestMethod.GET)
  public ResponseEntity<ResponseDetails> fetchStudentTestQuestion(
      @PathVariable String testReference) throws CommException {
    LOGGER.info("Enter CommController.fetchStudentTestQuestion() " + testReference);
    return commService.fetchStudentTestQuestion(testReference);
  }


  /**
   * This reource is used to save result. This also gives call the service layer method
   * setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/save-result_1", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> saveTestResults_1(@RequestBody ResultDTO resultDTO)
      throws CommException {
    LOGGER.info("Enter CommController.saveTestResults() " + resultDTO);
    return commService.saveTestResults_1(resultDTO);
  }

  /**
   * This reource is used to save result. This also gives call the service layer method
   * setReourceService().
   *
   * @return ResponseEntity
   */
  @RequestMapping(value = "/save-result", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> saveTestResults(@RequestBody TestResult testResult) {

    LOGGER.info("Enter CommController.saveTestResults() " + testResult);
    LOGGER.info("Enter CommController.saveTestResults() - testResult.getQuestion()" + testResult
        .getQuestion());
    return commService.saveTestResults(testResult);
  }

  /**
   * This reource is used to fetch  result. This also gives call the service layer method
   * setReourceService().
   */
  @RequestMapping(value = "/get-result", method = RequestMethod.POST)
  public ResponseEntity<ResponseDetails> getTestResults(@RequestBody ResultDTO resultDTO)
      throws CommException {
    LOGGER.info("Enter CommController.getTestResults() " + resultDTO);
    return commService.fetchTestResult(resultDTO);
  }

  /**
   * This reource is used to fetch  result. This also gives call the service layer method
   * setReourceService().
   */
  @RequestMapping(value = "/get-notification", method = RequestMethod.GET)
  public ResponseEntity<ResponseDetails> getNotifications() throws CommException {
    LOGGER.info("Enter CommController.getNotifications() ");
    return commService.fetchNotification();
  }
}
