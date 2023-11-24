package com.example.secondSeminar.category.dto.response;

import com.example.secondSeminar.category.domain.Category;

public record CategoryResponse(
        String content
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getContent()
        );
    }
}
