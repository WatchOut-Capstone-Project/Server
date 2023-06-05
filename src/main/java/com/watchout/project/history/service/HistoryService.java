package com.watchout.project.history.service;

import com.watchout.project.common.exception.DBFailException;
import com.watchout.project.common.exception.NotFoundException;
import com.watchout.project.common.response.SuccessResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.common.response.success.SuccessCode;
import com.watchout.project.history.domain.History;
import com.watchout.project.history.domain.repository.HistoryRepository;
import com.watchout.project.history.domain.repository.HistoryRepositoryImpl;
import com.watchout.project.history.service.dto.*;
import com.watchout.project.keyword.domain.Keyword;
import com.watchout.project.user.domain.User;
import com.watchout.project.user.domain.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final Logger LOGGER = LoggerFactory.getLogger(HistoryService.class);
    private final UserRepositoryImpl userRepositoryImpl;
    private final HistoryRepository historyRepository;
    private final HistoryRepositoryImpl historyRepositoryImpl;

    @Transactional
    public SuperResponse createHistory(HistoryUpdateRequestDto historyUpdateRequestDto) {

        LOGGER.info("[HistoryService] 키워드 매칭 확인 시도");

        User user = getUser(historyUpdateRequestDto.getPhoneNumber());

        List<Keyword> keywords = user.getKeywords();

        for (Keyword keyword : keywords) {
            if (historyUpdateRequestDto.getText().contains(keyword.getKeyword())) {

                LOGGER.info("[HistoryService] 키워드 매칭 확인 성공");

                History history = historyRepositoryImpl.findHistoryByPhoneNumberAndKeyword(historyUpdateRequestDto.getPhoneNumber(), keyword.getKeyword());

                if (history != null) {
                    LOGGER.info("[HistoryService] 키워드 기록 업데이트 시도");

                    int previousCallCount = history.getCallCount();
                    history.setCallCount(previousCallCount + 1);

                    String currentTime = createdAtFormatting(LocalDateTime.now()) + "시 ~";

                    history.getCallTimes().add(currentTime);

                    History updatedHistory = historyRepository.save(history);

                    LOGGER.info("[HistoryService] 키워드 기록 업데이트 성공");

                    return SuccessResponse.success(SuccessCode.HISTORY_UPDATE_SUCCESS, null);
                }
                else {
                    LOGGER.info("[HistoryService] 키워드 기록 생성 시도");
                    // 해당 키워드에 대해서 History가 없을 때
                    History newHistory = new History(historyUpdateRequestDto);

                    String currentTime = createdAtFormatting(LocalDateTime.now()) + "시 ~";
                    newHistory.getCallTimes().add(currentTime);

                    newHistory.setKeyword(keyword);

                    newHistory.setUser(user);

                    History savedHistory = historyRepository.save(newHistory);

                    if (savedHistory == null) {
                        throw new DBFailException("키워드 기록 생성 과정에서 오류가 발생했습니다. 다시 시도해 주세요.", ErrorCode.DB_FAIL_HISTORY_CREATE_FAIL_EXCEPTION);
                    }

                    LOGGER.info("[HistoryService] 키워드 기록 생성 성공");

                    return SuccessResponse.success(SuccessCode.HISTORY_CREATE_SUCCESS, null);
                }
            }
        }

        LOGGER.info("[HistoryService] 키워드 매칭 없음");

        return SuccessResponse.success(SuccessCode.NO_MATCH_KEYWORD_SUCCESS, null);
    }

    @Transactional
    public SuperResponse getHistory(HistoryRequestDto historyRequestDto) {
        LOGGER.info("[HistoryService] 키워드 count 조회 시도");

        List<History> histories = getHistories(historyRequestDto.getPhoneNumber());

        List<HistoryResponseDto> historyResponseDtos = new ArrayList<>();

        for (History history : histories) {
            historyResponseDtos.add(new HistoryResponseDto(history));
        }

        return SuccessResponse.success(SuccessCode.GET_HISTORIES_SUCCESS, new HistoryListResponseDto(historyResponseDtos));
    }

    @Transactional
    public SuperResponse checkKeywordConfirm(HistoryConfirmRequestDto historyConfirmRequestDto) {

        History history = historyRepositoryImpl.findHistoryByPhoneNumberAndKeyword(historyConfirmRequestDto.getPhoneNumber(), historyConfirmRequestDto.getKeyword());

        if (history == null) {
            throw new NotFoundException("존재하지 않는 키워드 기록 입니다.", ErrorCode.NOT_FOUND_HISTORY_EXCEPTION);
        }

        int prevConfirmCount = history.getConfirmCount();
        history.setConfirmCount(prevConfirmCount + 1);

        History updatedHistory = historyRepository.save(history);

        return SuccessResponse.success(SuccessCode.HISTORY_UPDATE_SUCCESS, null);
    }


    private User getUser(String phoneNumber) {
        User user = userRepositoryImpl.findUserByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new NotFoundException("탈퇴했거나 존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION);
        }

        return user;
    }

    private String createdAtFormatting(LocalDateTime createdAt) {
        return createdAt.format(DateTimeFormatter.ofPattern("HH"));
    }

    private List<History> getHistories(String phoneNumber) {

        User user = getUser(phoneNumber);

        List<History> histories = user.getHistories();

        return histories;
    }

}
