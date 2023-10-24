package com.example.secondSeminar.member.dto.response;

import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.member.domain.SOPT;

public record MemberResponse(
        String name,
        String nicknmae,
        int age,
        SOPT soptInfo
) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}