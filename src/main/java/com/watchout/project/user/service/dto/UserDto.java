package com.watchout.project.user.service.dto;

import com.watchout.project.history.domain.History;
import com.watchout.project.keyword.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserDto {

    private String phoneNumber;
    private List<Keyword> keywords;
    private List<History> histories;

}
