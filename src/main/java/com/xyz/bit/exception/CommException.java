package com.xyz.bit.exception;

public class CommException extends RuntimeException {

  public CommException() {
    super();
  }

  public CommException(String message) {
    super(message);
  }

  public CommException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommException(Throwable cause) {
    super(cause);
  }
}


