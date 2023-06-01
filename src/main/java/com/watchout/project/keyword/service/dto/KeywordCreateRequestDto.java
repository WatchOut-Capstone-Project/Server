package com.watchout.project.keyword.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordCreateRequestDto {

    private String phoneNumber;
    private String keyword;

}
