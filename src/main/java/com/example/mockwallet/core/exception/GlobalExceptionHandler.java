package com.example.mockwallet.core.exception;

import com.example.mockwallet.core.exception.WalletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(WalletException.class)
  public ResponseEntity<String> walletExceptionHandler(WalletException e) {
    /*
    todo:
    should probably respond with an error code, which gets logged with the actual error
    add request to logging
    handle other types of errors
    */
    return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getType().getHttpStatus()));
  }
}
