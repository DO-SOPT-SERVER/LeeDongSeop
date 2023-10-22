package com.example.secondSeminar.dto.request;

import com.example.secondSeminar.domain.Member.SOPT;
import lombok.Data;

@Data
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
