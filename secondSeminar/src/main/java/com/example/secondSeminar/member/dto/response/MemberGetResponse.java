package com.example.secondSeminar.member.dto.response;

import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.member.domain.SOPT;

public record MemberGetResponse(
        String name,
        String nicknmae,
        int age,
        SOPT soptInfo
) {
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}