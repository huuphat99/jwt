package com.example.demo.controller.base;

import com.example.demo.component.ErrorsProcessor;
import com.example.demo.handler.ValidationException;
import com.example.demo.model.ErrorModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Log4j2
public abstract class BaseController {

    @Autowired
    private ErrorsProcessor errorsProcessor;

    /**
     * Create Fail Response for Bean Validation Errors.
     *
     * @param errors {@link Errors}
     */
    public void createFailResponse(Errors errors) {
        ErrorModel errorModel = this.errorsProcessor.processErrors(errors);
        throw new ValidationException(errorModel);
    }
}