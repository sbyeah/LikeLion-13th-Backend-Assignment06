package com.likelion.junseoungbin_new.member.application;

import java.util.List;
import com.likelion.junseoungbin_new.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.junseoungbin_new.member.api.dto.response.MemberListResponseDto;
import com.likelion.junseoungbin_new.member.domain.Member;
import com.likelion.junseoungbin_new.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 정보 저장
    @Transactional
    public void memberSave(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = Member.builder()
                .nickname(memberSaveRequestDto.nickname())
                .age(memberSaveRequestDto.age())
                .part(memberSaveRequestDto.part())
                .build();
        memberRepository.save(member);
    }

    // 사용자 모두 조회
    public MemberListResponseDto memberFindAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberInfoResponseDto> memberInfoResponseDtoList = members.stream()
                .map(MemberInfoResponseDto::from)
                .toList();
        return MemberListResponseDto.from(memberInfoResponseDtoList);
    }

    // 단일 사용자 조회
    public MemberInfoResponseDto memberFindOne(Long memberId) {
        Member member = findMemberById(memberId);
        return MemberInfoResponseDto.from(member);
    }

    // 사용자 정보 수정
    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = findMemberById(memberId);
        member.update(memberUpdateRequestDto);
    }

    // 사용자 정보 삭제
    @Transactional
    public void memberDelete(Long memberId) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다. ID: " + memberId));
    }
}
