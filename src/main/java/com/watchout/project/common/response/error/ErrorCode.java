package com.watchout.project.common.response.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.watchout.project.common.response.error.ErrorStatusCode.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 400 Bad Request
     */
    VALIDATION_EXCEPTION(BAD_REQUEST, "잘못된 요청입니다."),

    /**
     * 401 UnAuthorized
     */
    UNAUTHORIZED_EXCEPTION(UNAUTHORIZED, "토큰이 존재하지 않습니다. 다시 로그인 해주세요."),
    UNAUTHORIZED_INVALID_TOKEN_EXCEPTION(UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    /**
     * 402 DB Fail
     */
    DB_FAIL_EXCEPTION(DB_HANDLE_FAIL, "DB처리 과정에서 오류가 발생했습니다. 다시 시도해 주세요."),
    DB_FAIL_USER_CREATE_FAIL_EXCEPTION(DB_HANDLE_FAIL, "유저 생성 과정에서 오류가 발생했습니다. 다시 시도해 주세요."),
    DB_FAIL_KEYWORD_CREATE_FAIL_EXCEPTION(DB_HANDLE_FAIL, "키워드 생성 과정에서 오류가 발생했습니다. 다시 시도해 주세요."),

    /**
     * 403 Forbidden
     */
    FORBIDDEN_EXCEPTION(FORBIDDEN, "허용하지 않는 요청입니다."),

    /**
     * 404 Not Found
     */
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않습니다."),
    NOT_FOUND_USER_EXCEPTION(NOT_FOUND, "탈퇴했거나 존재하지 않는 유저입니다."),
    NOT_FOUND_KEYWORD_EXCEPTION(NOT_FOUND, "존재하지 않는 키워드입니다."),


    /**
     * 405 Method Not Allowed
     */
    METHOD_NOT_ALLOWED_EXCEPTION(METHOD_NOT_ALLOWED, "지원하지 않는 메소드 입니다."),

    /**
     * 406 Not Acceptable
     */
    NOT_ACCEPTABLE_EXCEPTION(NOT_ACCEPTABLE, "Not Acceptable"),
    NOT_ACCEPTABLE_ALREADY_EXIST_PHONE_NUMBER_EXCEPTION(NOT_ACCEPTABLE, "이미 등록된 번호 입니다."),

    /**
     * 407 PAYMENT_SERVER_ERROR
     */
    PAYMENT_SERVER_CONNECT_ERROR(PAYMENT_SERVER_ERROR, "결제 서버와의 통신에서 오류가 발생했습니다."),
    PAYMENT_CANCEL_WHILE_PAYMENT_ERROR(PAYMENT_SERVER_ERROR, "결제 도중에 결제가 취소되었습니다."),

    /**
     * 408 FILE_CONVERT_ERROR
     */
    FILE_CONVERT_ERROR(FILE_CONVERTED_ERROR, "파일 변환과정에서 오류가 발생했습니다."),

    /**
     * 409 Conflict
     */
    CONFLICT_EMAIL_EXCEPTION(CONFLICT, "이미 존재하는 이메일 입니다."),
    CONFLICT_USER_EXCEPTION(CONFLICT, "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요."),

    /**
     * 415 Unsupported Media Type
     */
    UNSUPPORTED_MEDIA_TYPE_EXCEPTION(UNSUPPORTED_MEDIA_TYPE, "해당하는 미디어 타입을 지원하지 않습니다."),

    /**
     * 416 Unsupported Encoding
     */
    UNSUPPORTED_ENCODING_EXCEPTION(UNSUPPORTED_ENCODING, "해당하는 인코딩을 지원하지 않습니다."),

    /**
     * 417 No Such Algorithm
     */
    NO_SUCH_ALGORITHM_EXCEPTION(NO_SUCH_ALGORITHM, "해당하는 알고리즘이 없습니다."),

    /**
     * 418 Invalid Key
     */
    INVALID_KEY_EXCEPTION(INVALID_KEY, "유효하지 않은 키 입니다."),

    /**
     * 419 Json processing
     */
    JSON_PROCESSING_EXCEPTION(JSON_PROCESSING, "Json 파일 프로세싱 과정에서 오류가 발생했습니다."),

    /**
     * 500 Internal Server Exception
     */
    INTERNAL_SERVER_EXCEPTION(INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    /**
     * 502 Bad Gateway
     */
    BAD_GATEWAY_EXCEPTION(BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),

    /**
     * 503 Service UnAvailable
     */
    SERVICE_UNAVAILABLE_EXCEPTION(SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final ErrorStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
