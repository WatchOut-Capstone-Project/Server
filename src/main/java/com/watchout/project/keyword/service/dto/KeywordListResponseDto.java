package com.watchout.project.keyword.service.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordListResponseDto {

    public KeywordListResponseDto(List<String> keywords) {
        this.keywords = keywords;
    }

    private List<String> keywords;

}
