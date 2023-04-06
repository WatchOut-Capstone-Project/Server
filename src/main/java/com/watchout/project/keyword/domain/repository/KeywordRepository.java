package com.watchout.project.keyword.domain.repository;

import com.watchout.project.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
