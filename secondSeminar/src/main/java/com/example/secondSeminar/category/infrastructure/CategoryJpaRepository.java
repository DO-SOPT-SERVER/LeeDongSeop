package com.example.secondSeminar.category.infrastructure;

import com.example.secondSeminar.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Short> {
}
