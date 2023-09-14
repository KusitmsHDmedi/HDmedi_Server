package com.kusithm.hdmedi_server.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "children_id")
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private LocalDate birthday;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Children createChildren(User user, String name, Gender gender, LocalDate birthday){
        Children createdChildren = Children.builder()
                .name(name)
                .gender(gender)
                .birthday(birthday)
                .build();
        user.addChildren(createdChildren);
        return createdChildren;
    }
}
