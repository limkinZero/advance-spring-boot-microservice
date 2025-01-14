package com.almis.auth.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ElementNotFoundException(String message) {
    super(message);
  }
}
