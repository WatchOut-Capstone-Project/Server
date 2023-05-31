package com.watchout.project.user.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.watchout.project.common.auditing.AuditingTimeEntity;
import com.watchout.project.history.domain.History;
import com.watchout.project.keyword.domain.Keyword;
import com.watchout.project.user.controller.dto.UserCreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_TABLE")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User extends AuditingTimeEntity {

    public User(UserCreateRequestDto userCreateRequestDto) {
        this.phoneNumber = userCreateRequestDto.getPhoneNumber();
        this.keywords = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Keyword> keywords = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<History> histories = new ArrayList<>();

}
