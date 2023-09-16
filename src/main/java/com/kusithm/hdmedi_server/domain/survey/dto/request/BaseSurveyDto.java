package com.kusithm.hdmedi_server.domain.survey.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseSurveyDto {
    private String question;
    private int score;
}
