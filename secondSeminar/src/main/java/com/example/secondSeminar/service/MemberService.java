package com.example.secondSeminar.service;

import com.example.secondSeminar.domain.Member.Member;
import com.example.secondSeminar.domain.Member.SOPT;
import com.example.secondSeminar.dto.request.MemberCreateRequest;
import com.example.secondSeminar.dto.request.MemberProfileUpdateRequest;
import com.example.secondSeminar.dto.response.MemberGetResponse;
import com.example.secondSeminar.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getMemberByIdV1(Long id) {
        Member member = memberJpaRepository.findById(id).get();
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getMemberByIdV2(Long memberId) {
        return MemberGetResponse.of(findById(memberId));
    }

    private Member findById(Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("해당하는 회원이 없습니다.")
        );
    }

    public MemberGetResponse getMemberByIdV3(Long id) {
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(id));
    }

    public List<MemberGetResponse> getMembers() {
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(MemberCreateRequest request) {
        Member member =  memberJpaRepository.save(Member.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .age(request.getAge())
                .sopt(request.getSopt())
                .build());
        return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequest request) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(request.getGeneration(), request.getPart()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }
}
