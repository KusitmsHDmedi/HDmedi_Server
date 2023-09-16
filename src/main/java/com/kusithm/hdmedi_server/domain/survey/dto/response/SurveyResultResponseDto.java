package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SurveyResultResponseDto {
    private String message;
    private int score;
}
