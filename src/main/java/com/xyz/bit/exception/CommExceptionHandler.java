package com.xyz.bit.exception;

import com.xyz.bit.dto.ResponseDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommExceptionHandler {

  @Autowired
  ResponseDetails responseDetails;

  @ExceptionHandler(value = {Exception.class})
  @ResponseBody
  public ResponseEntity<ResponseDetails> handleAnyException() {
    responseDetails = setResponse(500, "Internal server error", null);
    return errorResponse(responseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {CommException.class})
  @ResponseBody
  public ResponseEntity<ResponseDetails> handleRuntimeFailures() {
    responseDetails = setResponse(500, "Internal server error", null);
    return errorResponse(responseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  protected ResponseEntity<ResponseDetails> errorResponse(ResponseDetails responseDetails,
      HttpStatus status) {
    return response(responseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  protected ResponseEntity<ResponseDetails> response(ResponseDetails responseDetails,
      HttpStatus status) {
    return new ResponseEntity<ResponseDetails>(responseDetails, status);
  }

  protected ResponseDetails setResponse(int code, String description,
      List<ResponseDetails> listAddressLookupResponseModel) {
    responseDetails.setId(code);
    responseDetails.setDetails(description);
    return responseDetails;
  }
}
