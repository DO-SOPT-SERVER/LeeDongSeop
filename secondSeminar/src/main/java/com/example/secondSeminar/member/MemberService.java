package com.example.secondSeminar.member;

import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.member.domain.SOPT;
import com.example.secondSeminar.member.dto.request.MemberCreateRequest;
import com.example.secondSeminar.member.dto.request.MemberProfileUpdateRequest;
import com.example.secondSeminar.member.dto.response.MemberResponse;
import com.example.secondSeminar.member.infrastructure.MemberJpaRepository;
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

//    public MemberGetResponse getMemberByIdV1(Long id) {
//        Member member = memberJpaRepository.findById(id).get();
//        return MemberGetResponse.of(member);
//    }
//
//    public MemberGetResponse getMemberByIdV2(Long memberId) {
//        return MemberGetResponse.of(findById(memberId));
//    }
//
//    private Member findById(Long memberId) {
//        return memberJpaRepository.findById(memberId).orElseThrow(
//                () -> new EntityNotFoundException("해당하는 회원이 없습니다.")
//        );
//    }

    public MemberResponse getMemberById(Long id) {
        return MemberResponse.of(memberJpaRepository.findByIdOrThrow(id));
    }

    public List<MemberResponse> getMembers() {
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {
        Member member =  memberJpaRepository.save(Member.of(request));
        return MemberResponse.of(member);
    }

    @Transactional
    public MemberResponse updateSOPT(Long memberId, MemberProfileUpdateRequest request) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(request.generation(), request.part()));
        return MemberResponse.of(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }
}
