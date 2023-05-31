package com.watchout.project.user.domain.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.watchout.project.user.domain.User;
import lombok.RequiredArgsConstructor;

import static com.watchout.project.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public boolean checkUserByPhoneNumber(String phoneNumber) {
        return jpqlQueryFactory
                .selectFrom(user)
                .where(
                        user.phoneNumber.eq(phoneNumber)
                )
                .distinct()
                .fetchOne() != null;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return jpqlQueryFactory
                .selectFrom(user)
                .where(
                        user.phoneNumber.eq(phoneNumber)
                )
                .distinct()
                .fetchOne();
    }
}
