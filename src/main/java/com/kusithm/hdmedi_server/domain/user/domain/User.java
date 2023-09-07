package com.kusithm.hdmedi_server.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Platform platform;
    private String platformId;

    public static User createUser(Platform platform, String platformId){
        return User.builder()
                .platform(platform)
                .platformId(platformId)
                .build();
    }
}
