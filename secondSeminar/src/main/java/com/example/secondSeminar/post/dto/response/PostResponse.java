package com.example.secondSeminar.post.dto.response;

import com.example.secondSeminar.category.domain.Category;
import com.example.secondSeminar.post.domain.Post;

public record PostResponse(
        Long id,
        String title,
        String content,
        String category
) {
    public static PostResponse of(Post post, Category category) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                category.getContent()
        );
    }
}
