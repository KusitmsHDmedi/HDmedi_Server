package com.kusithm.hdmedi_server.domain.survey.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreateSurveyDto {
    private List<BaseSurveyDto> questionList;
    private int totalScore;
}
