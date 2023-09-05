package com.kusithm.hdmedi_server.domain.sample.dto.response;

import com.kusithm.hdmedi_server.domain.sample.domain.Sample;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SampleResponseDto {
    private Long id;
    private String data;

    @Builder
    public SampleResponseDto(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public static SampleResponseDto of(Sample sample) {
        return SampleResponseDto.builder()
                .id(sample.getId())
                .data(sample.getData())
                .build();
    }
}
