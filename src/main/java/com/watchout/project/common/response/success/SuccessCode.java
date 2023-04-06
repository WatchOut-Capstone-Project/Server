package com.watchout.project.common.response.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.watchout.project.common.response.success.SuccessStatusCode.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    SUCCESS(OK, "성공입니다."),
    GET_USER_SUCCESS(OK, "user : 유저 조회 성공입니다."),

    // 인증
    LOGIN_SUCCESS(OK, "signIn : 로그인 성공입니다."),

    /**
     * 201 CREATED
     */
    USER_CREATE_SUCCESS(CREATED, "user : 유저 생성 성공입니다."),
    /**
     * 202 ACCEPTED
     */
    SIGN_UP_EMAIL_CHECK_SUCCESS(ACCEPTED, "sign-up/email-check : 사용가능한 이메일 입니다.")

    /**
     * 204 NO_CONTENT
     */
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
