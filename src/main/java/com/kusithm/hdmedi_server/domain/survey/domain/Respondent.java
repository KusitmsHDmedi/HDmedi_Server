package com.kusithm.hdmedi_server.domain.survey.domain;

import com.kusithm.hdmedi_server.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class Respondent{
    private List<BaseSurvey> baseSurveyList;
    private int totalScore;
    private LocalDate createDate;

    public static Respondent createRespondent(List<BaseSurvey> baseSurveyList, int totalScore, LocalDate localDate) {
        return Respondent.builder()
                .baseSurveyList(baseSurveyList)
                .totalScore(totalScore)
                .createDate(localDate)
                .build();
    }

}
