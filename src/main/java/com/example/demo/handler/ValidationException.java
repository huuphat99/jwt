package com.example.demo.handler;

import com.example.demo.model.ErrorModel;
import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception for validate error
 */
@Getter
public class ValidationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6916053056972075023L;
  private final String errorCode;
  private final String errorMessage;

  /**
   * Default constructor with {@link ErrorModel} and {@link HttpStatus}.
   *
   * @param errorModel {@link ErrorModel}
   */
  public ValidationException(ErrorModel errorModel) {
    this.errorMessage = errorModel.getErrorMessage();
    this.errorCode = errorModel.getErrorCode();
  }
}
