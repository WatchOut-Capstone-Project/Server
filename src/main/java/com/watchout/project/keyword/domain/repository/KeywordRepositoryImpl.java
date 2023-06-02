package com.watchout.project.keyword.domain.repository;

import static com.watchout.project.keyword.domain.QKeyword.keyword1;

import com.querydsl.jpa.JPQLQueryFactory;
import com.watchout.project.keyword.domain.Keyword;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepositoryCustom {

    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public Keyword findKeywordByKeywordId(Long keywordId) {
        return jpqlQueryFactory
                .selectFrom(keyword1)
                .where(
                        keyword1.id.eq(keywordId)
                )
                .distinct()
                .fetchOne();
    }
}
