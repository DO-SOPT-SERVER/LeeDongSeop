package com.example.secondSeminar.dto.response;

import com.example.secondSeminar.domain.Member.Member;
import com.example.secondSeminar.domain.Member.SOPT;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberGetResponse {

    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;

    // DTO 생성을 위한 팩토리 method -> Service 계층에서 짧은 코드로 Member Entity를 만들 수 있다.
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
