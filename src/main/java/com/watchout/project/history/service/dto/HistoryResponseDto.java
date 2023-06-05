package com.watchout.project.history.service.dto;

import com.watchout.project.history.domain.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoryResponseDto {

    public HistoryResponseDto(History history) {
        this.keyword = history.getKeyword().getKeyword();
        this.callCount = history.getCallCount();
        this.confirmCount = history.getConfirmCount();
        this.callTimes = history.getCallTimes();
    }

    private String keyword;
    private int callCount;
    private int confirmCount;
    private List<String> callTimes;

}
