package com.watchout.project.keyword.domain.repository;

import com.watchout.project.keyword.domain.Keyword;

public interface KeywordRepositoryCustom {

    Keyword findKeywordByKeywordId(Long keywordId);

}
