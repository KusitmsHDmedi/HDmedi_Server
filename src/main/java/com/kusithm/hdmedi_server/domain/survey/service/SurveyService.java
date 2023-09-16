package com.kusithm.hdmedi_server.domain.survey.service;

import com.kusithm.hdmedi_server.domain.survey.domain.BaseSurvey;
import com.kusithm.hdmedi_server.domain.survey.domain.Respondent;
import com.kusithm.hdmedi_server.domain.survey.domain.Survey;
import com.kusithm.hdmedi_server.domain.survey.dto.request.BaseSurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.request.CreateSurveyDto;
import com.kusithm.hdmedi_server.domain.survey.repository.SurveyRepository;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.error.exception.EntityNotFoundException;
import com.kusithm.hdmedi_server.global.error.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public void processSurvey(HDmediUser hDmediUser, CreateSurveyDto createSurveyDto) {
        List<BaseSurvey> baseSurveyList = createBaseSurvey(createSurveyDto.getQuestionList());
        Respondent respondent = Respondent.createRespondent(baseSurveyList, createSurveyDto.getTotalScore());
        Survey currentSurvey = surveyRepository.findByUserId(hDmediUser.getId()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
        if(hDmediUser.getIsGuest()){
            currentSurvey.getEachSurvey().addTeacherSurveyList(respondent);
        }else {
            currentSurvey.getEachSurvey().addParentsSurveyList(respondent);
        }
    }

    private List<BaseSurvey> createBaseSurvey(List<BaseSurveyDto> questionList){
        return questionList.stream()
                .map(baseSurveyDto ->
                        BaseSurvey.createBaseSurvey(baseSurveyDto.getQuestion(), baseSurveyDto.getScore()))
                .collect(Collectors.toList());
    }

}


