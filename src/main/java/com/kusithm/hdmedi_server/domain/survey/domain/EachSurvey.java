package com.kusithm.hdmedi_server.domain.survey.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Data
public class EachSurvey {
    private List<Respondent> parentsSurveyList;
    private List<Respondent> teacherSurveyList;

    public static EachSurvey createEachSurvey() {
        return EachSurvey.builder()
                .parentsSurveyList(new ArrayList<>())
                .teacherSurveyList(new ArrayList<>())
                .build();
    }

    public void addParentsSurveyList(Respondent parentsSurvey) {
        this.parentsSurveyList.add(parentsSurvey);
    }

    public void addTeacherSurveyList(Respondent teacherSurvey) {
        this.teacherSurveyList.add(teacherSurvey);
    }
}
