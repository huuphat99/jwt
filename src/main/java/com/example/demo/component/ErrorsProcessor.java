package com.example.demo.component;

import com.example.demo.model.ErrorModel;
import com.example.demo.model.ErrorResponse;
import com.example.demo.security.ApplicationConstants;
import com.example.demo.security.ApplicationUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * ErrorsKeyConverter.
 *
 * @author quannl
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class ErrorsProcessor {

  private static final String FIELD_LABEL_PARAM_NAME = "[FIELD_LABEL]";

  private final MessageSource messageSource;

  /**
   * Create Fail Response for Bean Validation Errors.
   *
   * @param errors {@link Errors}
   * @return a fail response for the current service
   */
  public ResponseEntity<ErrorResponse> createFailResponse(Errors errors) {
    ErrorModel errorModel = new ErrorModel();
    try {
      errorModel = this.processErrors(errors);
    } catch (Exception ex) {
      log.warn("Error when processing errors");
      log.warn(ex.getMessage(), ex);
    }
    ErrorResponse errorResponse = ApplicationUtils.createFailResponse(errorModel);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Convert bean validation error into error model.
   *
   * @param errors {@link Errors}
   * @return a list of error model
   */
  public ErrorModel processErrors(Errors errors) {
    List<ErrorModel> results = new ArrayList<>();
    List<ErrorModel> fieldErrors = this.processFieldErrors(errors);
    if (!CollectionUtils.isEmpty(fieldErrors)) {
      results.addAll(fieldErrors);
    }
    List<ErrorModel> globalErrors = this.processGlobalErrors(errors);
    if (!CollectionUtils.isEmpty(globalErrors)) {
      results.addAll(globalErrors);
    }
    if (results.isEmpty()) {
      return new ErrorModel();
    }
    results.sort(Comparator.comparing(ErrorModel::getErrorCode));
    return results.get(0);
  }

  /**
   * process Global validation errors.
   *
   * @param errors {@link Errors}
   */
  private List<ErrorModel> processGlobalErrors(Errors errors) {
    List<ErrorModel> errorModels = new ArrayList<>();
    if (errors != null && errors.hasErrors()) {
      errorModels = errors.getGlobalErrors().stream().map(this::processGlobalError).toList();
    }
    return errorModels;
  }

  /**
   * Get message content from a global error and return a error model.
   *
   * @param error {@link ObjectError}
   */
  private ErrorModel processGlobalError(ObjectError error) {
    String value = ApplicationConstants.UNDEFINED_ERROR_MESSAGE_ID;
    String errorCode = ApplicationConstants.UNDEFINED_ERROR_MESSAGE;

    try {
      errorCode = Optional.ofNullable(error.getCode())
          .orElse(ApplicationConstants.UNDEFINED_ERROR_MESSAGE_ID);
      value = this.messageSource.getMessage(errorCode, error.getArguments(),
          LocaleContextHolder.getLocale());
    } catch (Exception ex) {
      log.warn(ex.getMessage(), ex);
    }

    return new ErrorModel(errorCode, value);
  }

  /**
   * Replace all the messages which have [FIELD_LABEL] string.
   *
   * @param errors {@link BindingResult} instance
   * @throws NoSuchFieldException   from Java Reflect API
   * @throws IllegalAccessException from Java Reflect API
   */
  private List<ErrorModel> processFieldErrors(Errors errors) {
    List<ErrorModel> errorModels = new ArrayList<>();
    if (errors != null && errors.hasFieldErrors()) {
      errorModels = errors.getFieldErrors().stream().map(this::processFieldError).toList();
    }
    return errorModels;
  }

  /**
   * Replace the [FIELD_LABEL] string with the object field.
   *
   * @param error {@link FieldError}
   * @return an error model
   */
  private ErrorModel processFieldError(FieldError error) {
    String value = "";
    String errorCode = null;
    try {
      errorCode = Optional.ofNullable(error.getDefaultMessage()).orElse("");
      value = this.messageSource.getMessage(errorCode, error.getArguments(),
          LocaleContextHolder.getLocale());
    } catch (Exception ex) {
      // do nothing
      log.warn(ex.getMessage(), ex);
    }

    if (StringUtils.isEmpty(value)) {
      value = error.getDefaultMessage();
      errorCode = Optional.ofNullable(error.getCode())
          .orElse(ApplicationConstants.UNDEFINED_ERROR_MESSAGE_ID);
    } else {
      if (value.contains(FIELD_LABEL_PARAM_NAME)) {
        value = value.replace(FIELD_LABEL_PARAM_NAME, error.getField());
      }
    }

    return new ErrorModel(errorCode, value);
  }
}
