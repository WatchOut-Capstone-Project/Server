package com.watchout.project.common.response;

import com.watchout.project.common.response.success.SuccessCode;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> extends SuperResponse {
    public static final SuccessResponse<String> SUCCESS = success(SuccessCode.SUCCESS, null);

    private int status;
    private boolean success;
    private String message;
    private T data;

    public static <T> SuccessResponse<T> success(SuccessCode successCode, T data) {
        return new SuccessResponse<>(successCode.getStatus(), true, successCode.getMessage(), data);
    }

//    @Override
//    public Object getDate() {
//        return this.getData();
//    }
}
