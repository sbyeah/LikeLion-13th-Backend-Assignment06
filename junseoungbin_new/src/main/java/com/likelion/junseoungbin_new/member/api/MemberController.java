package com.likelion.junseoungbin_new.member.api;

import com.likelion.junseoungbin_new.common.error.SuccessCode;
import com.likelion.junseoungbin_new.common.template.ApiResTemplate;
import com.likelion.junseoungbin_new.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberListResponseDto;
import com.likelion.junseoungbin_new.member.appliocation.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 사용자 저장
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResTemplate<String> memberSave(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        memberService.memberSave(memberSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MEMBER_SAVE_SUCCESS);
    }

    // 사용자 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<MemberListResponseDto> memberFindAll(
            @PageableDefault(size = 10, sort = "memberId", direction = Sort.Direction.ASC)
            Pageable pageable) {
        MemberListResponseDto memberListResponseDto = memberService.memberFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberListResponseDto);
    }

    // 회원 id를 통해 특정 사용자 조회
    @GetMapping("/{memberId}")
    public ApiResTemplate<MemberInfoResponseDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.memberFindOne(memberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberInfoResponseDto);
    }


    // 회원 id를 통한 사용자 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<String> memberUpdate(@PathVariable("memberId") Long memberId,
                                               @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.memberUpdate(memberId, memberUpdateRequestDto);
        return new ResponseEntity<>(SuccessCode.MEMBER_UPDATE_SUCCESS.getMessage(), SuccessCode.MEMBER_UPDATE_SUCCESS.getHttpStatus());
    }

    // 회원 id를 통한 사용자 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);
        return new ResponseEntity<>(SuccessCode.MEMBER_DELETE_SUCCESS.getMessage(), SuccessCode.MEMBER_DELETE_SUCCESS.getHttpStatus());
    }
