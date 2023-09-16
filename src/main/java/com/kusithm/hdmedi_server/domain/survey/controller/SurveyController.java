package com.kusithm.hdmedi_server.domain.survey.controller;

import com.kusithm.hdmedi_server.domain.survey.dto.request.CreateSurveyDto;
import com.kusithm.hdmedi_server.domain.survey.dto.response.SurveyDetailResultDto;
import com.kusithm.hdmedi_server.domain.survey.dto.response.SurveyResultResponseDto;
import com.kusithm.hdmedi_server.domain.survey.service.SurveyService;
import com.kusithm.hdmedi_server.global.common.BaseResponse;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.common.SuccessCode;
import com.kusithm.hdmedi_server.global.config.auth.AuthenticatedUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/survey")
@Controller
public class SurveyController {
    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> submitSurvey(@AuthenticatedUserId final HDmediUser hDmediUser,
                                                        @RequestBody final CreateSurveyDto requestDto) {
       final SurveyResultResponseDto responseDto =  surveyService.processSurvey(hDmediUser, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.of(SuccessCode.CREATED, responseDto));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<?>> getAllSurveys() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, null));
    }

    @GetMapping("/result")
    public ResponseEntity<BaseResponse<?>> getSurveyResult(@AuthenticatedUserId final HDmediUser hDmediUser,
                                                           @RequestParam final Long surveyId) {
        final SurveyDetailResultDto responseDto = surveyService.getDetailResult(hDmediUser, surveyId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, responseDto));
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<?>> getSurveyContent(@RequestParam final Long surveyId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, null));
    }
}