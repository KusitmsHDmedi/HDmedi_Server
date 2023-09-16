package com.kusithm.hdmedi_server.domain.survey.controller;

import com.kusithm.hdmedi_server.domain.survey.domain.SurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.response.SurveyResultResponseDto;
import com.kusithm.hdmedi_server.domain.survey.repository.Survey;
import com.kusithm.hdmedi_server.domain.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    //설문 결과 저장
    @PostMapping("/submit-survey")
    public ResponseEntity<SurveyResultResponseDto> submitSurvey(@ModelAttribute SurveyDto surveyDTO) {
        SurveyResultResponseDto result = surveyService.processSurvey(surveyDTO);
        return ResponseEntity.ok(result);
    }
    // 설문 결과를 전송
    @GetMapping("/get-survey-result")
    public ResponseEntity<Object> getSurveyResult() {
        SurveyResultResponseDto result = surveyService.getSurveyResult();
        return ResponseEntity.ok(result);
    }
    // 모든 설문 데이터를 조회
    @GetMapping("/")
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

    // 설문 상세 조회 - 결과 조회
    @GetMapping("/{surveyId}/result")
    public ResponseEntity<SurveyResultResponseDto> getSurveyResult(@PathVariable Long surveyId) {
        SurveyResultResponseDto result = surveyService.getSurveyResultById(surveyId);
        return ResponseEntity.ok(result);
    }

    // 설문 상세 조회 - 내용 조회
    @GetMapping("/{surveyId}/content")
    public ResponseEntity<Survey> getSurveyContent(@PathVariable Long surveyId) {
        Survey survey = surveyService.getSurveyById(surveyId);
        return ResponseEntity.ok(survey);
    }
}