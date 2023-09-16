package com.kusithm.hdmedi_server.domain.survey.service;

import com.kusithm.hdmedi_server.domain.survey.domain.BaseSurvey;
import com.kusithm.hdmedi_server.domain.survey.domain.Respondent;
import com.kusithm.hdmedi_server.domain.survey.domain.Survey;
import com.kusithm.hdmedi_server.domain.survey.dto.request.BaseSurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.request.CreateSurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.response.*;
import com.kusithm.hdmedi_server.domain.survey.repository.SurveyRepository;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.common.MessageSourceProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Transactional
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final MessageSourceProvider messageSourceProvider;

    public SurveyResultResponseDto processSurvey(HDmediUser hDmediUser, CreateSurveyDto createSurveyDto) {
        List<BaseSurvey> baseSurveyList = createBaseSurvey(createSurveyDto.getQuestionList());
        Respondent respondent = Respondent.createRespondent(baseSurveyList, createSurveyDto.getTotalScore(), LocalDate.now());
        Survey currentSurvey = findSurvey(hDmediUser.getId());
        addRespondentSurvey(hDmediUser.getIsGuest(), currentSurvey, respondent);
        saveSurvey(currentSurvey);
        String surveyResultMessage = getMessageFor(respondent.getTotalScore());
        return SurveyResultResponseDto.of(respondent.getTotalScore(), surveyResultMessage);
    }

    public AllSurveyResponseDto getAllSurvey(HDmediUser hDmediUser) {
        Survey currentSurvey = findSurvey(hDmediUser.getId());
        List<BaseAllSurveyResponseDto> baseAllSurveyResponseDtoList = getBaseAllSurveyResponseDtoList(currentSurvey);
        return AllSurveyResponseDto.of(baseAllSurveyResponseDtoList);
    }

    public SurveyDetailResultDto getDetailResult(HDmediUser hDmediUser, Long surveyId) {
        Survey currentSurvey = findSurvey(hDmediUser.getId());
        Respondent parentsRespondent = getParentsRespondent(currentSurvey, surveyId.intValue());
        Respondent teacherRespondent = getTeacherRespondent(currentSurvey, surveyId.intValue());
        String parentsMessage = getMessageFor(parentsRespondent.getTotalScore());
        String teacherMessage = getMessageForTeacherSurvey(parentsRespondent.getTotalScore());
        return SurveyDetailResultDto.of(parentsRespondent.getTotalScore(), parentsMessage, teacherRespondent.getTotalScore(), teacherMessage);
    }

    public SurveyDetailResponseDto getSurveyDetail(HDmediUser hDmediUser, Long surveyId) {
        Survey currentSurvey = findSurvey(hDmediUser.getId());
        Respondent parentsRespondent = getParentsRespondent(currentSurvey, surveyId.intValue());
        Respondent teacherRespondent = getTeacherRespondent(currentSurvey, surveyId.intValue());
        List<BaseSurveyResponseDto> parentsBaseSurveyResponseDtoList = getBaseSurveyDtoListFor(parentsRespondent);
        List<BaseSurveyResponseDto> teachersBaseSurveyResponseDtoList = getBaseSurveyDtoListFor(teacherRespondent);
        return SurveyDetailResponseDto.of(parentsBaseSurveyResponseDtoList, teachersBaseSurveyResponseDtoList);
    }

    private List<BaseAllSurveyResponseDto> getBaseAllSurveyResponseDtoList(Survey currentSurvey) {
        List<Respondent> respondentList = currentSurvey.getEachSurvey().getParentsSurveyList();
        List<BaseAllSurveyResponseDto> responseDtoList = IntStream.range(0, respondentList.size())
                .mapToObj(idx -> BaseAllSurveyResponseDto.of(
                        respondentList.get(idx).getCreateDate(),
                        Long.valueOf(idx)
                ))
                .collect(Collectors.toList());
        return responseDtoList;
    }

    private List<BaseSurveyResponseDto> getBaseSurveyDtoListFor(Respondent respondent) {
        return respondent.getBaseSurveyList().stream()
                .map(baseSurvey ->
                        BaseSurveyResponseDto.of(baseSurvey.getQuestion(), baseSurvey.getScore()))
                .collect(Collectors.toList());
    }

    private String getMessageFor(int totalScore) {
        if (totalScore >= 19) return messageSourceProvider.getEmergencyContent();
        return messageSourceProvider.getNonEmergencyContent();
    }

    private String getMessageForTeacherSurvey(int totalScore) {
        if (totalScore == -1) return messageSourceProvider.getNonSurveyContent();
        if (totalScore >= 19) return messageSourceProvider.getEmergencyContent();
        return messageSourceProvider.getNonEmergencyContent();
    }

    private Respondent getParentsRespondent(Survey currentSurvey, int id) {
        return currentSurvey.getEachSurvey().getParentsSurveyList().get(id);
    }

    private Respondent getTeacherRespondent(Survey currentSurvey, int id) {
        List<Respondent> teacherSurveyList = currentSurvey.getEachSurvey().getTeacherSurveyList();
        if (teacherSurveyList.size() <= id) return Respondent.createRespondent(null, -1, LocalDate.now());
        return teacherSurveyList.get(id);
    }

    private List<BaseSurvey> createBaseSurvey(List<BaseSurveyDto> questionList) {
        return questionList.stream()
                .map(baseSurveyDto ->
                        BaseSurvey.createBaseSurvey(baseSurveyDto.getQuestion(), baseSurveyDto.getScore()))
                .collect(Collectors.toList());
    }

    private Survey findSurvey(Long userId) {
        return surveyRepository.findByUserId(userId)
                .orElseGet(() -> Survey.createSurvey(userId));
    }

    private void addRespondentSurvey(Boolean isGuest, Survey currentSurvey, Respondent respondent) {
        if (isGuest)
            currentSurvey.getEachSurvey().addTeacherSurveyList(respondent);
        else
            currentSurvey.getEachSurvey().addParentsSurveyList(respondent);
    }

    private void saveSurvey(Survey survey) {
        surveyRepository.save(survey);
    }


}


