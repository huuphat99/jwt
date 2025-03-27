package com.example.demo.handler;

import com.example.demo.model.ErrorModel;
import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception for user not found.
 */
@Getter
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8581890558314028487L;

    private final String errorCode;
    private final String errorMessage;

    /**
     * Default constructor with {@link ErrorModel} and {@link HttpStatus}.
     *
     * @param errorModel {@link ErrorModel}
     */
    public NotFoundException(ErrorModel errorModel) {
        this.errorMessage = errorModel.getErrorMessage();
        this.errorCode = errorModel.getErrorCode();
    }
}
