package com.example.secondSeminar.dto.request;

import com.example.secondSeminar.domain.Member.SOPT;

public record MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
}

