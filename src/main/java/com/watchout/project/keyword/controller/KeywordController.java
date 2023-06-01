package com.watchout.project.keyword.controller;

import com.watchout.project.common.exception.BoilerplateException;
import com.watchout.project.common.response.ErrorResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.keyword.service.KeywordService;
import com.watchout.project.keyword.service.dto.KeywordCreateRequestDto;
import com.watchout.project.keyword.service.dto.KeywordListRequestDto;
import com.watchout.project.keyword.service.dto.KeywordListResponseDto;
import com.watchout.project.user.controller.dto.UserCreateRequestDto;
import com.watchout.project.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Keyword")
@RequestMapping("/keyword")
@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final KeywordService keywordService;

    @ApiOperation("Keyword : 키워드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Keyword : 키워드 생성 성공"),
            @ApiResponse(code = 402, message = "키워드 생성 과정에서 오류가 발생했습니다."),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다."),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생했습니다.")
    })
    @PostMapping("")
    public SuperResponse createUser(@RequestBody KeywordCreateRequestDto keywordCreateRequestDto) {
        LOGGER.info("[KeywordController] 키워드 생성 시도");

        SuperResponse keywordCreateResponse;
        try {
            keywordCreateResponse = keywordService.createKeyword(keywordCreateRequestDto);
        } catch (BoilerplateException boilerplateException) {
            System.out.println(boilerplateException.getErrorCode());
            return ErrorResponse.error(boilerplateException.getErrorCode());
        } catch (Exception exception) {
            return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
        LOGGER.info("[KeywordController] 키워드 생성 성공");

        return keywordCreateResponse;
    }


    @ApiOperation("Keyword : 키워드 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Keyword : 키워드 리스트 조회 성공"),
            @ApiResponse(code = 402, message = "키워드 생성 과정에서 오류가 발생했습니다."),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다."),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생했습니다.")
    })
    @PostMapping("/keywords")
    public SuperResponse getKeywords(@RequestBody KeywordListRequestDto keywordListRequestDto) {
        LOGGER.info("[KeywordController] 키워드 리스트 조회 시도");

        SuperResponse getKeywordsResponse;
        try {
            getKeywordsResponse = keywordService.getKeywords(keywordListRequestDto);
        } catch (BoilerplateException boilerplateException) {
            System.out.println(boilerplateException.getErrorCode());
            return ErrorResponse.error(boilerplateException.getErrorCode());
        } catch (Exception exception) {
            return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
        LOGGER.info("[KeywordController] 키워드 리스트 조회 성공");

        return getKeywordsResponse;
    }

}
