package com.kusithm.hdmedi_server.domain.survey.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AllSurveyResponseDto {
    private List<BaseAllSurveyResponseDto> allSurveyList;
    public static AllSurveyResponseDto of(List<BaseAllSurveyResponseDto> allSurveyList){
        return AllSurveyResponseDto.builder()
                .allSurveyList(allSurveyList)
                .build();
    }
}
