package com.movie.category.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.category.domain.Category;
import com.movie.category.domain.CategoryId;
import com.movie.category.exception.AlreadyExistCategoryException;
import com.movie.category.repository.CategoryRepository;

@Service
public class CreateCategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category createCategory(String category) {
		if (categoryRepository.existsById(new CategoryId(category))) {
			throw new AlreadyExistCategoryException(category);
		}
		return categoryRepository.save(new Category(new CategoryId(category)));
	}
}
