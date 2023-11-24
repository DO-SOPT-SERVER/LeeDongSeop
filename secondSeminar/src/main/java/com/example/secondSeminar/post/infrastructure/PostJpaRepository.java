package com.example.secondSeminar.post.infrastructure;

import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);
}
