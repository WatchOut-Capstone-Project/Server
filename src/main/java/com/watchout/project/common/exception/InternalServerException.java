package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class InternalServerException extends BoilerplateException {

    public InternalServerException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }

    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
