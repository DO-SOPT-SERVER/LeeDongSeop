package com.example.secondSeminar.member;

import com.example.secondSeminar.common.exception.dto.ApiResponse;
import com.example.secondSeminar.member.dto.request.MemberCreateRequest;
import com.example.secondSeminar.member.dto.request.MemberProfileUpdateRequest;
import com.example.secondSeminar.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.secondSeminar.common.exception.SuccessType.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    // 특정 사용자 정보 단건 조회 V1
//    @GetMapping("/{memberId}")
//    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
//        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
//    }
//
//    // 특정 사용자 정보 단건 조회 V2
//    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
//        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
//    }
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResponse> getMemberProfile(@PathVariable Long memberId) {
        return ApiResponse.success(GET_MEMBER_SUCCESS, memberService.getMemberById(memberId));
    }

    // 목록 조회
    @GetMapping
    public ApiResponse<List<MemberResponse>> getMembersProfile() {
        return ApiResponse.success(GET_MEMBER_LIST_SUCCESS, memberService.getMembers());
    }

    // 생성
    @PostMapping
    public ApiResponse<MemberResponse> createMember(@RequestBody MemberCreateRequest request) {
        return ApiResponse.success(CREATE_MEMBER_SUCCESS, memberService.create(request));
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ApiResponse<MemberResponse> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
        return ApiResponse.success(UPDATE_MEMBER_SUCCESS, memberService.updateSOPT(memberId, request));
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ApiResponse<?> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.success(DELETE_MEMBER_SUCCESS);
    }
}
