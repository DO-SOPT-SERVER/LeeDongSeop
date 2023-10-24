package com.example.secondSeminar.dto.response;

import com.example.secondSeminar.domain.Member.Member;
import com.example.secondSeminar.domain.Member.SOPT;

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