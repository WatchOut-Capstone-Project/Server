package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class FileConvertException extends BoilerplateException {

    public FileConvertException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public FileConvertException(String message) {
        super(message, ErrorCode.DB_FAIL_EXCEPTION);
    }
}
