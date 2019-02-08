package com.movie.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.category.domain.Category;
import com.movie.category.domain.CategoryId;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

}
