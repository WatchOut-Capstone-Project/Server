package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class ValidationException extends BoilerplateException {

    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }
}
