package com.watchout.project.common.exception;

import com.watchout.project.common.response.error.ErrorCode;

public class PaymentServerException extends BoilerplateException {

    public PaymentServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public PaymentServerException(String message) {
        super(message, ErrorCode.PAYMENT_SERVER_CONNECT_ERROR);
    }
}
