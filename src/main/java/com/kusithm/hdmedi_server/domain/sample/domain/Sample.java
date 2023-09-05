package com.kusithm.hdmedi_server.domain.sample.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Sample {
    private Long id;
    private String data;
    @Builder
    public Sample(Long id, String data) {
        this.id = id;
        this.data = data;
    }
}
