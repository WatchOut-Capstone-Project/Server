package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class UnAuthorizedException extends BoilerplateException {

    public UnAuthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
