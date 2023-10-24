package com.example.secondSeminar.member;

import com.example.secondSeminar.member.dto.request.MemberCreateRequest;
import com.example.secondSeminar.member.dto.request.MemberProfileUpdateRequest;
import com.example.secondSeminar.member.dto.response.MemberGetResponse;
import com.example.secondSeminar.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    // 특정 사용자 정보 단건 조회 V1
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    // 특정 사용자 정보 단건 조회 V2
    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    // 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        URI location =  URI.create(memberService.create(request));
        return ResponseEntity.created(location).build();
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
