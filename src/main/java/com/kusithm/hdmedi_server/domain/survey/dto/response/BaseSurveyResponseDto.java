package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseSurveyResponseDto {
    private String question;
    private int score;

    public static BaseSurveyResponseDto of(String question, int score){
        return BaseSurveyResponseDto.builder()
                .question(question)
                .score(score)
                .build();
    }
}
