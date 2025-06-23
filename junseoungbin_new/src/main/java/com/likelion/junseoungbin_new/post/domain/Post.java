package com.likelion.junseoungbin_new.post.domain;

import com.likelion.junseoungbin_new.member.domain.Member;
import com.likelion.junseoungbin_new.post.api.dto.request.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String pros;

    private String cons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Post(String pros, String cons, Member member) {
        this.pros = pros;
        this.cons = cons;
        this.member = member;
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        this.pros = postUpdateRequestDto.pros();
        this.cons = postUpdateRequestDto.cons();
    }
}