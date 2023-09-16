package com.kusithm.hdmedi_server.domain.survey.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Document(collection = "survey")
public class Survey {
    @Id
    private String id;
    private Long userId;
    private EachSurvey eachSurvey;

    public static Survey createSurvey(Long userId){
        EachSurvey createdEachSurvey = EachSurvey.createEachSurvey();
        return Survey.builder()
                .userId(userId)
                .eachSurvey(createdEachSurvey)
                .build();
    }
}
