package com.kusithm.hdmedi_server.domain.survey.domain;

import com.kusithm.hdmedi_server.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Respondent extends BaseTimeEntity {
    private List<BaseSurvey> baseSurveyList = new ArrayList<>();
    private int totalScore;

    public static Respondent createRespondent(List<BaseSurvey> baseSurveyList, int totalScore){
        return Respondent.builder()
                .baseSurveyList(baseSurveyList)
                .totalScore(totalScore)
                .build();
    }

}
