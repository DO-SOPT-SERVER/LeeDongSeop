package com.example.secondSeminar.member.dto.request;

import com.example.secondSeminar.member.domain.enums.Part;

public record MemberProfileUpdateRequest(int generation, Part part) {
}

