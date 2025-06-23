package com.likelion.junseoungbin_new.member.api;

import com.likelion.junseoungbin_new.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberListResponseDto;
import com.likelion.junseoungbin_new.member.application.MemberService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> memberSave(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        memberService.memberSave(memberSaveRequestDto);
        return new ResponseEntity<>("사용자 저장!", HttpStatus.CREATED);
    }

    // 사용자 전체 조회
    @GetMapping("/all")
    public ResponseEntity<MemberListResponseDto> memberFindAll() {
        MemberListResponseDto memberListResponseDto = memberService.memberFindAll();
        return new ResponseEntity<>(memberListResponseDto, HttpStatus.OK);
    }

    // 회원 id를 통해 특정 사용자 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoResponseDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.memberFindOne(memberId);
        return new ResponseEntity<>(memberInfoResponseDto, HttpStatus.OK);
    }

    // 회원 id를 통한 사용자 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<String> memberUpdate(@PathVariable("memberId") Long memberId,
                                               @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.memberUpdate(memberId, memberUpdateRequestDto);
        return new ResponseEntity<>("사용자 수정", HttpStatus.OK);
    }

    // 회원 id를 통한 사용자 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);
        return new ResponseEntity<>("사용자 삭제", HttpStatus.OK);
    }
}