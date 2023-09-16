package com.kusithm.hdmedi_server.domain.survey.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SurveyDetailResponseDto {
    private List<BaseSurveyResponseDto> parentsSurveyList;
    private List<BaseSurveyResponseDto> teacherSurveyList;

    public static SurveyDetailResponseDto of(List<BaseSurveyResponseDto> parentsSurveyList, List<BaseSurveyResponseDto> teacherSurveyList){
        return SurveyDetailResponseDto.builder()
                .parentsSurveyList(parentsSurveyList)
                .teacherSurveyList(teacherSurveyList)
                .build();
    }
}
