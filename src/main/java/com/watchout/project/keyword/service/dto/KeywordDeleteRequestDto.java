package com.watchout.project.keyword.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordDeleteRequestDto {

    private Long keywordId;
    private String phoneNumber;

}
