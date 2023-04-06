package com.watchout.project.common.exception;


import com.watchout.project.common.response.error.ErrorCode;

public class ConflictEmailException extends BoilerplateException {

    public ConflictEmailException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictEmailException(String message) {
        super(message, ErrorCode.CONFLICT_EMAIL_EXCEPTION);
    }
}
