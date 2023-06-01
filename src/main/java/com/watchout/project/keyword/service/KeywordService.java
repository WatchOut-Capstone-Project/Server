package com.watchout.project.keyword.service;

import com.watchout.project.common.exception.DBFailException;
import com.watchout.project.common.exception.NotAcceptableException;
import com.watchout.project.common.exception.NotFoundException;
import com.watchout.project.common.response.SuccessResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.common.response.success.SuccessCode;
import com.watchout.project.keyword.domain.Keyword;
import com.watchout.project.keyword.domain.repository.KeywordRepository;
import com.watchout.project.keyword.service.dto.KeywordCreateRequestDto;
import com.watchout.project.keyword.service.dto.KeywordListRequestDto;
import com.watchout.project.keyword.service.dto.KeywordListResponseDto;
import com.watchout.project.user.controller.dto.UserCreateRequestDto;
import com.watchout.project.user.domain.User;
import com.watchout.project.user.domain.repository.UserRepository;
import com.watchout.project.user.domain.repository.UserRepositoryImpl;
import com.watchout.project.user.service.UserService;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final KeywordRepository keywordRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final UserRepository userRepository;

    @Transactional
    public SuperResponse createKeyword(KeywordCreateRequestDto keywordCreateRequestDto) {
        LOGGER.info("[KeywordService] 키워드 생성 시도");

        Keyword keyword = new Keyword(keywordCreateRequestDto);

        User user = userRepositoryImpl.findUserByPhoneNumber(keywordCreateRequestDto.getPhoneNumber());

        if (user == null) {
            throw new NotFoundException("탈퇴했거나 존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION);
        }

        Keyword savedKeyword = keywordRepository.save(keyword);

        if (savedKeyword == null) {
            throw new DBFailException("키워드 생성 과정에서 오류가 발생했습니다. 다시 시도해 주세요.", ErrorCode.DB_FAIL_KEYWORD_CREATE_FAIL_EXCEPTION);
        }

        user.addKeyword(savedKeyword);

        User updatedUser = userRepository.save(user);

        LOGGER.info("[KeywordService] 키워드 생성 성공");

        return SuccessResponse.success(SuccessCode.KEYWORD_CREATE_SUCCESS, null);
    }

    @Transactional
    public SuperResponse getKeywords(KeywordListRequestDto keywordListRequestDto) {

        LOGGER.info("[KeywordService] 키워드 리스트 조회 시도");

        User user = userRepositoryImpl.findUserByPhoneNumber(keywordListRequestDto.getPhoneNumber());

        if (user == null) {
            throw new NotFoundException("탈퇴했거나 존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION);
        }

        List<Keyword> keywords = user.getKeywords();

        List<String> keywordList = new ArrayList<>();

        for (Keyword keyword : keywords) {
            keywordList.add(keyword.getKeyword());
        }

        return SuccessResponse.success(SuccessCode.GET_KEYWORDS_SUCCESS, new KeywordListResponseDto(keywordList));
    }

}
