package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SurveyResultResponseDto {
    private int totalScore;
    private String message;

    public static SurveyResultResponseDto of(int totalScore, String message) {
        return SurveyResultResponseDto.builder()
                .totalScore(totalScore)
                .message(message)
                .build();
    }
}
