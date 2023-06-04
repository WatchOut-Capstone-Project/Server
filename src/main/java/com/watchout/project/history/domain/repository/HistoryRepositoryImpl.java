package com.watchout.project.history.domain.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.watchout.project.history.domain.History;
import lombok.RequiredArgsConstructor;

import static com.watchout.project.history.domain.QHistory.history;
import static com.watchout.project.keyword.domain.QKeyword.keyword1;
import static com.watchout.project.user.domain.QUser.user;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {

    private final JPQLQueryFactory jpqlQueryFactory;


    @Override
    public History findHistoryByPhoneNumberAndKeyword(String phoneNumber, String keyword) {
        return jpqlQueryFactory
                .selectFrom(history)
                .leftJoin(history.keyword, keyword1)
                .fetchJoin()
                .leftJoin(history.user, user)
                .fetchJoin()
                .where(
                        history.keyword.keyword.eq(keyword),
                        history.user.phoneNumber.eq(phoneNumber)
                )
                .distinct()
                .fetchOne();
    }
}
