package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BaseAllSurveyResponseDto {
    private LocalDate date;
    private Long surveyId;

    public static BaseAllSurveyResponseDto of(LocalDate date, Long surveyId) {
        return BaseAllSurveyResponseDto.builder()
                .date(date)
                .surveyId(surveyId)
                .build();
    }

}
