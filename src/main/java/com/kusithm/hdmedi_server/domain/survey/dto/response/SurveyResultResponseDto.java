package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SurveyResultResponseDto {
    private int totalScore;

    public static SurveyResultResponseDto of(int totalScore){
        return SurveyResultResponseDto.builder()
                .totalScore(totalScore)
                .build();
    }
}
