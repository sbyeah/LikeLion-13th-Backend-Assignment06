package com.likelion.junseoungbin_new.post.domain.repository;

import com.likelion.junseoungbin_new.member.domain.Member;
import com.likelion.junseoungbin_new.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);
}