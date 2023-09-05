package com.kusithm.hdmedi_server.domain.sample.service;

import com.kusithm.hdmedi_server.domain.sample.domain.Sample;
import com.kusithm.hdmedi_server.domain.sample.dto.request.SampleRequestDto;
import com.kusithm.hdmedi_server.domain.sample.dto.response.SampleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SampleService {

    public SampleResponseDto createSample(SampleRequestDto sampleRequestDto) {
        Sample sample = sampleRequestDto.toEntity();
        return SampleResponseDto.of(sample);
    }
}
