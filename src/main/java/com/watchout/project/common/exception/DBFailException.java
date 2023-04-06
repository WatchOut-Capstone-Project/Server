package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class DBFailException extends BoilerplateException {

    public DBFailException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public DBFailException(String message) {
        super(message, ErrorCode.DB_FAIL_EXCEPTION);
    }
}
