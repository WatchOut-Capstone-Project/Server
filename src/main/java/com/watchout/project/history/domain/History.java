package com.watchout.project.history.domain;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.watchout.project.common.auditing.AuditingTimeEntity;
import com.watchout.project.history.service.dto.HistoryUpdateRequestDto;
import com.watchout.project.keyword.domain.Keyword;
import com.watchout.project.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "HISTORY_TABLE")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class History extends AuditingTimeEntity {

    public History(HistoryUpdateRequestDto historyUpdateRequestDto) {
        this.phoneNumber = historyUpdateRequestDto.getPhoneNumber();
        this.keyword = null;
        this.callCount = 1;
        this.confirmCount = 0;
        this.callTimes = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private int callCount;

    private int confirmCount;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "call_time")
    private List<String> callTimes;

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;

    }

    public void deleteKeyword() {
        this.keyword = null;
    }

    public void setUser(User user) {
        this.user = user;

        if (user.getHistories() == null) {
            user.setHistories(new ArrayList<>());
        }

        user.getHistories().add(this);
    }

    public void deleteUser(User user) {
        this.user = null;

        if (user.getHistories().contains(this)) {
            user.getHistories().remove(this);
        }
    }

}
