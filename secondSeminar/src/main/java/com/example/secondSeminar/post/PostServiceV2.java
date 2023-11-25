package com.example.secondSeminar.post;

import com.example.secondSeminar.common.exception.model.BusinessException;
import com.example.secondSeminar.externel.S3Service;
import com.example.secondSeminar.member.domain.Member;
import com.example.secondSeminar.member.infrastructure.MemberJpaRepository;
import com.example.secondSeminar.post.domain.Post;
import com.example.secondSeminar.post.dto.request.PostCreateRequest;
import com.example.secondSeminar.post.infrastructure.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.secondSeminar.common.exception.enums.ErrorType.NOT_FOUND_POST_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/"; // -> 폴더링을 하는게 성능이 더 빠르다!
//    "posts/1member/1.jpg"
//    "posts/2member/1.jpg"
//    "posts/3member/1.jpg"
//    "posts/4member/1.jpg"
//    "posts/5member/1.jpg" -> 이렇게 올리는게 조회 속도가 더 빠르다!

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberJpaRepository.findByIdOrThrow(memberId);
            Post post = postJpaRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build());
            return post.getPostId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postJpaRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException(NOT_FOUND_POST_ERROR));
            s3Service.deleteImage(post.getImageUrl());
            postJpaRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}