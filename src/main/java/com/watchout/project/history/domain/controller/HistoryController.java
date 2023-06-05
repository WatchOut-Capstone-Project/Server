package com.watchout.project.history.domain.controller;


import com.watchout.project.common.exception.BoilerplateException;
import com.watchout.project.common.response.ErrorResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.history.service.HistoryService;
import com.watchout.project.history.service.dto.HistoryRequestDto;
import com.watchout.project.history.service.dto.HistoryUpdateRequestDto;
import com.watchout.project.keyword.service.dto.KeywordCreateRequestDto;
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

@Api(tags = "History")
@RequestMapping("/history")
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);
    private final HistoryService historyService;

    @ApiOperation("History : 키워드 기록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "History : 키워드 기록 생성 성공"),
            @ApiResponse(code = 201, message = "History : 키워드 기록 업데이트 성공"),
            @ApiResponse(code = 204, message = "History : 매칭되는 키워드는 없습니다."),
            @ApiResponse(code = 402, message = "키워드 기록 생성 과정에서 오류가 발생했습니다. 다시 시도해 주세요."),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생했습니다.")
    })
    @PostMapping("")
    public SuperResponse createHistory(@RequestBody HistoryUpdateRequestDto historyUpdateRequestDto) {
        LOGGER.info("[HistoryController] 키워드 매칭 확인 시도");

        SuperResponse historyCreateResponse;
        try {
            historyCreateResponse = historyService.createHistory(historyUpdateRequestDto);
        } catch (BoilerplateException boilerplateException) {
            return ErrorResponse.error(boilerplateException.getErrorCode());
        } catch (Exception exception) {
            return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
        LOGGER.info("[HistoryController] 키워드 매칭 확인 성공");

        return historyCreateResponse;
    }


    @ApiOperation("History : 키워드 기록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "History : 키워드 기록 조회 성공"),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다."),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생했습니다.")
    })
    @PostMapping("/histories")
    public SuperResponse getHistories(@RequestBody HistoryRequestDto historyRequestDto) {
        LOGGER.info("[HistoryController] 키워드 기록 조회 시도");

        SuperResponse getHistoriesResponse;
        try {
            getHistoriesResponse = historyService.getHistory(historyRequestDto);
        } catch (BoilerplateException boilerplateException) {
            return ErrorResponse.error(boilerplateException.getErrorCode());
        } catch (Exception exception) {
            return ErrorResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
        LOGGER.info("[HistoryController] 키워드 기록 조회 성공");

        return getHistoriesResponse;
    }

}
