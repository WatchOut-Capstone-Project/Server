package com.watchout.project.user.service;

import com.watchout.project.common.exception.DBFailException;
import com.watchout.project.common.exception.NotAcceptableException;
import com.watchout.project.common.exception.NotFoundException;
import com.watchout.project.common.response.SuccessResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.common.response.success.SuccessCode;
import com.watchout.project.user.controller.dto.UserCreateRequestDto;
import com.watchout.project.user.domain.User;
import com.watchout.project.user.domain.repository.UserRepository;
import com.watchout.project.user.domain.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;

    @Transactional
    public SuperResponse createUser(UserCreateRequestDto userCreateRequestDto) {
        LOGGER.info("[UserService] 유저 생성 시도");

        if (userRepositoryImpl.checkUserByPhoneNumber(userCreateRequestDto.getPhoneNumber())) {
            throw new NotAcceptableException("이미 등록된 번호 입니다.", ErrorCode.NOT_ACCEPTABLE_ALREADY_EXIST_PHONE_NUMBER_EXCEPTION);
        }

        User user = new User(userCreateRequestDto);

        User savedUser = userRepository.save(user);

        if (savedUser == null) {
            throw new DBFailException("유저 생성 과정에서 오류가 발생했습니다.", ErrorCode.DB_FAIL_USER_CREATE_FAIL_EXCEPTION);
        }

        LOGGER.info("[UserService] 유저 생성 완료");

        return SuccessResponse.success(SuccessCode.USER_CREATE_SUCCESS, null);
    }


    public SuperResponse loginUser(UserCreateRequestDto userCreateRequestDto) {
        LOGGER.info("[UserService] 유저 로그인 시도");

        User user = userRepositoryImpl.findUserByPhoneNumber(userCreateRequestDto.getPhoneNumber());

        if (user == null) {
            throw new NotFoundException("탈퇴했거나 존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION);
        }

        LOGGER.info("[UserService] 유저 로그인 성공");

        return SuccessResponse.success(SuccessCode.LOGIN_SUCCESS, user.getId());
    }


}
