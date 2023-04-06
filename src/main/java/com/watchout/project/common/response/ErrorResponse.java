package com.watchout.project.common.response;

import com.watchout.project.common.response.error.ErrorCode;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse extends SuperResponse {

    private int status;
    private boolean success;
    private String message;

    public static ErrorResponse error(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), false, errorCode.getMessage());
    }

    public static ErrorResponse error(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getStatus(), false, message);
    }

//    @Override
//    public Object getDate() {
//        return null;
//    }
}
