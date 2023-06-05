package com.watchout.project.history.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoryListResponseDto {

    public HistoryListResponseDto(List<HistoryResponseDto> histories) {
        this.histories = histories;
    }

    private List<HistoryResponseDto> histories;

}
