package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class NotFoundException extends BoilerplateException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
