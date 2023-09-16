package com.kusithm.hdmedi_server.domain.survey.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
public class BaseSurvey {
    private String question;
    private int score;

    public static BaseSurvey createBaseSurvey(String question, int score){
        return BaseSurvey.builder()
                .question(question)
                .score(score)
                .build();
    }
}
