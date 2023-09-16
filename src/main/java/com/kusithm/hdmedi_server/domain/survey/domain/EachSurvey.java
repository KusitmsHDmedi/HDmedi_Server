package com.kusithm.hdmedi_server.domain.survey.domain;

import com.kusithm.hdmedi_server.global.common.BaseTimeEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EachSurvey {
    private List<Respondent> parentsSurveyList = new ArrayList<>();
    private List<Respondent> teacherSurveyList = new ArrayList<>();

    public void addParentsSurveyList(Respondent parentsSurvey){
        this.parentsSurveyList.add(parentsSurvey);
    }

    public void addTeacherSurveyList(Respondent teacherSurvey){
        this.teacherSurveyList.add(teacherSurvey);
    }
}
