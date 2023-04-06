package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BoilerplateException extends RuntimeException {

    private final ErrorCode errorCode;

    public BoilerplateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
