package com.watchout.project.history.domain.repository;

import com.watchout.project.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
