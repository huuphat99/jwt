package com.example.demo.model;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Common error message.
 *
 * @author quannl
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel implements Serializable {
    @Serial
    private static final long serialVersionUID = -2537909859084826345L;
    private String errorCode;
    private String errorMessage;

    public ErrorModel(String errorCode) {
        this.errorCode = errorCode;
    }
}
