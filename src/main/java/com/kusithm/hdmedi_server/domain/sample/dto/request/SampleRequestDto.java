package com.kusithm.hdmedi_server.domain.sample.dto.request;

import com.kusithm.hdmedi_server.domain.sample.domain.Sample;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SampleRequestDto {
    private Long id;
    private String data;

    @Builder
    public SampleRequestDto(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public Sample toEntity() {
        return Sample.builder()
                .id(id)
                .data(data)
                .build();
    }
}
