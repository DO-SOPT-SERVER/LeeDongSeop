package com.example.secondSeminar.member.dto.request;

import com.example.secondSeminar.common.enums.Part;

public record MemberProfileUpdateRequest(int generation, Part part) {
}

