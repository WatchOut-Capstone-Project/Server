package com.watchout.project.keyword.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordResponseDto {

    public KeywordResponseDto(Long keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }

    private Long keywordId;
    private String keyword;

}
