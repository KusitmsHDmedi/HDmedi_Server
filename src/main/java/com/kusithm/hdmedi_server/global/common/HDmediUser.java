package com.kusithm.hdmedi_server.global.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Data
public class HDmediUser {
    private Long id;
    private Boolean isGuest;

    public static HDmediUser of(Long id, Boolean isGuest) {
        return HDmediUser.builder()
                .id(id)
                .isGuest(isGuest)
                .build();
    }
}
