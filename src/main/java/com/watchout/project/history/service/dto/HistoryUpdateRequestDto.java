package com.watchout.project.history.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryUpdateRequestDto {

    private String phoneNumber;
    private String text;

}
