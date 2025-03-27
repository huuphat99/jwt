package com.example.demo.handler;

import com.example.demo.model.ErrorModel;
import com.example.demo.model.ErrorResponse;
import java.util.Locale;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * ApplicationExceptionHandler プロジェクトエラーハンドリングクラス.
 *
 * @author quannl
 */
@RestControllerAdvice
@Log4j2
public class ApplicationExceptionHandler extends DefaultErrorAttributes {

  private final MessageSource messageSource;

  public ApplicationExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
    ErrorModel model = getErrorModel(ex.getErrorCode(), null, ex);
    return new ResponseEntity<>(new ErrorResponse(model.getErrorCode(), model.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorModel model = getErrorModel("E99999", null, ex);
    log.error(ex);
    log.error(ex.getMessage());
    return new ResponseEntity<>(new ErrorResponse(model.getErrorCode(), model.getErrorMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(NotFoundException ex) {
    ErrorModel model = getErrorModel(ex.getErrorCode(), null, ex);
    return new ResponseEntity<>(new ErrorResponse(model.getErrorCode(), model.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  private ErrorResponse createError(String errorCode, @Nullable String[] params, Throwable ex) {
    ErrorResponse response = new ErrorResponse();
    ErrorModel error = getErrorModel(errorCode, params, ex);
    response.setErrorCode(error.getErrorCode());
    response.setErrorMessage(error.getErrorMessage());
    return response;
  }

  private ErrorModel getErrorModel(String errorCode, @Nullable String[] params, Throwable e) {
    return new ErrorModel(errorCode, this.writeLog(errorCode, params, e));
  }

  private String writeLog(String errorCode, @Nullable String[] params, Throwable e) {
    String errorMessage = this.messageSource.getMessage(errorCode, params, Locale.JAPAN);
    // Get the first character of the error code
    // We use it to determine the log level
    String logLevel = errorCode.substring(0, 1).toUpperCase();
    switch (logLevel) {
      case "W":
        log.warn(errorMessage);
        break;
      case "E":
        log.error(errorMessage, e);
        break;
      default:
        log.info(errorMessage);
        break;
    }
    return errorMessage;
  }

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest,
      ErrorAttributeOptions options) {
    ErrorModel errorModel = getErrorModel("E99999", null, null);
    return Map.of("errorMessage", errorModel.getErrorMessage(), "errorCode",
        errorModel.getErrorCode());
  }
}
