package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SurveyDetailResultDto {
    private int parentsScore;
    private String parentsMessage;
    private int teacherScore;
    private String teacherMessage;

    public static SurveyDetailResultDto of(int parentsScore, String parentsMessage, int teacherScore, String teacherMessage){
        return SurveyDetailResultDto.builder()
                .parentsScore(parentsScore)
                .parentsMessage(parentsMessage)
                .teacherScore(teacherScore)
                .teacherMessage(teacherMessage)
                .build();
    }
}
