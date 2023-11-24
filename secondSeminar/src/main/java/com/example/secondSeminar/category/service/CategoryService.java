package com.example.secondSeminar.category.service;

import com.example.secondSeminar.category.domain.Category;
import com.example.secondSeminar.category.domain.CategoryId;
import com.example.secondSeminar.category.dto.response.CategoryResponse;
import com.example.secondSeminar.category.infrastructure.CategoryJpaRepository;
import com.example.secondSeminar.common.exception.model.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.secondSeminar.common.exception.ErrorType.NOT_FOUND_CATEGORY_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryJpaRepository.findById(Short.valueOf(categoryId.getCategoryId())).orElseThrow(
                () -> new BusinessException(NOT_FOUND_CATEGORY_ERROR));
    }

    public CategoryResponse getById(Short id) {
        return CategoryResponse.of(categoryJpaRepository.findById(id).orElseThrow(
                () -> new BusinessException(NOT_FOUND_CATEGORY_ERROR)));
    }
}
