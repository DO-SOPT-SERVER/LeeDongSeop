package com.example.secondSeminar.post;

import com.example.secondSeminar.common.exception.model.BusinessException;
import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.member.infrastructure.MemberJpaRepository;
import com.example.secondSeminar.post.domain.Post;
import com.example.secondSeminar.post.dto.request.PostCreateRequest;
import com.example.secondSeminar.post.dto.request.PostUpdateRequest;
import com.example.secondSeminar.post.dto.response.PostResponse;
import com.example.secondSeminar.post.infrastructure.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.secondSeminar.common.exception.ErrorType.NOT_FOUND_POST_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public PostResponse create(PostCreateRequest request, Long memberId) {

        Member member = memberJpaRepository.findByIdOrThrow(memberId);

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .build();

        Post savedPost = postJpaRepository.save(post); // 영속 상태로 변경
        return PostResponse.of(savedPost);
    }

    public PostResponse getById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new BusinessException(NOT_FOUND_POST_ERROR));
        return PostResponse.of(post);
    }

    public List<PostResponse> getPosts(Long memberId) {
        return postJpaRepository.findAllByMemberId(memberId)
                .stream()
                .map(post -> PostResponse.of(post))
                .toList();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_POST_ERROR));
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new BusinessException(NOT_FOUND_POST_ERROR));
        postJpaRepository.delete(post);
    }
}
