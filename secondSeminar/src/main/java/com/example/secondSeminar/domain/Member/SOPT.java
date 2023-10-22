package com.example.secondSeminar.domain.Member;

import com.example.secondSeminar.enums.Part;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Embeddable // @Embedded랑 세트!
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SOPT {
    private int generation;

    @Enumerated(value = STRING) // 이걸 붙여주셔야 Part의 변수명으로 저장이 됩니다
    private Part part;
}