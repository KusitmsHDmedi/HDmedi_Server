package com.kusithm.hdmedi_server.domain.sample.controller;


import com.kusithm.hdmedi_server.domain.sample.dto.request.SampleRequestDto;
import com.kusithm.hdmedi_server.domain.sample.dto.response.SampleResponseDto;
import com.kusithm.hdmedi_server.domain.sample.service.SampleService;
import com.kusithm.hdmedi_server.global.common.BaseResponse;
import com.kusithm.hdmedi_server.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class SampleController {
    private final SampleService sampleService;

    @PostMapping("/sample")
    public ResponseEntity<BaseResponse<?>> createSample(@RequestBody final SampleRequestDto sampleRequestDto) {
        final SampleResponseDto sampleResponseDto = sampleService.createSample(sampleRequestDto);
        return ResponseEntity.ok(BaseResponse.of(SuccessCode.CREATED, sampleResponseDto));
    }
}

