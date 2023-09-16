package com.kusithm.hdmedi_server.domain.survey.service;

import com.kusithm.hdmedi_server.domain.survey.domain.SurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.response.SurveyResultResponseDto;
import com.kusithm.hdmedi_server.domain.survey.repository.Survey;
import com.kusithm.hdmedi_server.domain.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    public SurveyResultResponseDto processSurvey(SurveyDto surveyDTO) {
        Survey survey = new Survey();
        survey.setQuestion(surveyDTO.getQuestion());
        survey.setAnswer(surveyDTO.getAnswer());

        surveyRepository.save(survey);

        // 저장된 점수 계산 또는 다른 작업 수행
        int score = calculateScore(survey);

        // SurveyResultResponseDto 빌더를 사용하여 결과 생성
        return SurveyResultResponseDto.builder()
                .message("설문 결과가 성공적으로 저장되었습니다.")
                .score(score)
                .build();    }

    // 설문 결과를 조회하는 메서드 추가
    public SurveyResultResponseDto getSurveyResult() {
        // 데이터베이스에서 설문 결과를 조회하고, 결과 메시지와 저장된 점수를 생성하여 반환
        // 여기서는 임의의 값을 반환하도록 가정
        int score = 42;
        return SurveyResultResponseDto.builder()
                .message("설문 결과 조회가 성공적으로 완료되었습니다.")
                .score(score)
                .build();
    }
    private int calculateScore(Survey survey) {
        // 저장된 점수 계산 또는 다른 로직 수행
        // 여기서는 임의의 값을 반환하도록 가정
        return 42;
    }

    public List<Survey> getAllSurveys() {
        // 모든 설문 데이터를 조회하여 반환
        return surveyRepository.findAll();
    }

    public SurveyResultResponseDto getSurveyResultById(Long surveyId) {
        // surveyId를 사용하여 해당 설문 결과를 데이터베이스에서 조회
        // 조회한 결과를 SurveyResultResponseDto로 변환하여 반환
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("설문 데이터가 없습니다. ID: " + surveyId));

        // 저장된 점수 계산 또는 다른 작업 수행
        int score = calculateScore(survey);

        // SurveyResultResponseDto 빌더를 사용하여 결과 생성
        return SurveyResultResponseDto.builder()
                .message("설문 결과 조회가 성공적으로 완료되었습니다.")
                .score(score)
                .build();
    }

    public Survey getSurveyById(Long surveyId) {
        // surveyId를 사용하여 해당 설문 데이터를 데이터베이스에서 조회
        // 만약 해당 설문 데이터가 존재하지 않으면 예외를 던질 수 있습니다.
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("설문 데이터가 없습니다. ID: " + surveyId));
    }
}


