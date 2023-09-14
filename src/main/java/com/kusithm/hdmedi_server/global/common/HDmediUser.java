package com.kusithm.hdmedi_server.global.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HDmediUser {
    private final Long id;
    private final boolean isGuest;

    public static HDmediUser of(Long id, boolean isGuest){
        return HDmediUser.builder()
                .id(id)
                .isGuest(isGuest)
                .build();
    }
}
