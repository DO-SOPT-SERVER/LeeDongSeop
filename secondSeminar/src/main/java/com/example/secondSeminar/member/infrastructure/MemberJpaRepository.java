package com.example.secondSeminar.member.infrastructure;

import com.example.secondSeminar.common.exception.ErrorType;
import com.example.secondSeminar.common.exception.model.CustomException;
import com.example.secondSeminar.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorType.NOT_FOUND_MEMBER_ERROR));
    }
}