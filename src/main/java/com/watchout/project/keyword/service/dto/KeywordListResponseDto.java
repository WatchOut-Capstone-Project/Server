package com.watchout.project.keyword.service.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordListResponseDto {

    public KeywordListResponseDto(List<KeywordResponseDto> keywords) {
        this.keywords = keywords;
    }

    private List<KeywordResponseDto> keywords;

}
