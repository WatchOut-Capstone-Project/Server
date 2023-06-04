package com.watchout.project.history.domain.repository;

import com.watchout.project.history.domain.History;

public interface HistoryRepositoryCustom {

    History findHistoryByPhoneNumberAndKeyword(String phoneNumber, String keyword);

}
