package com.example.secondSeminar.member.dto.request;

import com.example.secondSeminar.member.domain.SOPT;

public record MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
}

