package com.example.secondSeminar.member.domain;

import com.example.secondSeminar.common.domain.BaseTimeEntity;
import com.example.secondSeminar.member.dto.request.MemberCreateRequest;
import com.example.secondSeminar.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @Embedded
    private SOPT sopt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, int age, SOPT sopt) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }

    public static Member of(MemberCreateRequest request) {
        return Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
    }

    public void updateSOPT(SOPT sopt) {
        this.sopt = sopt;
    }
}