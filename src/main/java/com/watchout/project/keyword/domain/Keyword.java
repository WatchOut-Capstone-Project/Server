package com.watchout.project.keyword.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.watchout.project.common.auditing.AuditingTimeEntity;
import com.watchout.project.keyword.service.dto.KeywordCreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "KEYWORD_TABLE")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Keyword extends AuditingTimeEntity {

    public Keyword(KeywordCreateRequestDto keywordCreateRequestDto) {
        this.keyword = keywordCreateRequestDto.getKeyword();
        this.callCount = 0;
        this.activate = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String keyword;

    private int callCount;

    private boolean activate;

}
