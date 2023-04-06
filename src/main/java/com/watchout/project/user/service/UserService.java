package com.watchout.project.user.service;

import com.watchout.project.common.exception.DBFailException;
import com.watchout.project.common.response.SuccessResponse;
import com.watchout.project.common.response.SuperResponse;
import com.watchout.project.common.response.error.ErrorCode;
import com.watchout.project.common.response.success.SuccessCode;
import com.watchout.project.user.controller.dto.UserCreateRequest;
import com.watchout.project.user.domain.User;
import com.watchout.project.user.domain.repository.UserRepository;
import com.watchout.project.user.domain.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;

    public SuperResponse createUser(UserCreateRequest userCreateRequest) {
        LOGGER.info("[UserService] 유저 생성 시도");

        User user = new User(userCreateRequest);

        User savedUser = userRepository.save(user);

        if (savedUser == null) {
            throw new DBFailException("유저 생성 과정에서 오류가 발생했습니다.", ErrorCode.DB_FAIL_USER_CREATE_FAIL_EXCEPTION);
        }

        return SuccessResponse.success(SuccessCode.USER_CREATE_SUCCESS, null);
    }


}
