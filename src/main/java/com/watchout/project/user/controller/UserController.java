package com.watchout.project.user.controller;

import com.watchout.project.common.exception.BoilerplateException;
import com.watchout.project.common.response.ErrorResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.user.controller.dto.UserCreateRequest;
import com.watchout.project.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserService userService;

    @ApiOperation("User : 유저를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User : 유저 생성 성공"),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생했습니다.")
    })
    @PostMapping("")
    public SuperResponse createUser(UserCreateRequest userCreateRequest) {
        LOGGER.info("[UserController] 유저 생성 시도");

        SuperResponse userCreateResponse;
        try {
            userCreateResponse = userService.createUser(userCreateRequest);
        } catch (BoilerplateException boilerplateException) {
            return ErrorResponse.error(boilerplateException.getErrorCode());
        } catch (Exception exception) {
            return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }

        return userCreateResponse;
    }

}
