package com.watchout.project.user.domain.repository;

import com.watchout.project.user.domain.User;

public interface UserRepositoryCustom {

    boolean checkUserByPhoneNumber(String phoneNumber);

    User findUserByPhoneNumber(String phoneNumber);

}
