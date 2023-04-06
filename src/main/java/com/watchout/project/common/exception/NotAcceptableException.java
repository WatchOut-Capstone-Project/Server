package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class NotAcceptableException extends BoilerplateException {

    public NotAcceptableException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotAcceptableException(String message) {
        super(message, ErrorCode.NOT_ACCEPTABLE_EXCEPTION);
    }
}
