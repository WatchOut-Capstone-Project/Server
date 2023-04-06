package com.watchout.project.user.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.watchout.project.common.auditing.AuditingTimeEntity;
import lombok.AccessLevel;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(length = 20, nullable = false, name = "tour_dates")
    private List<String> keywords = new ArrayList<>();

}
