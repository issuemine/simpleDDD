package com.movie.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.category.application.CreateCategoryService;
import com.movie.category.domain.Category;
import com.movie.category.domain.CategoryId;
import com.movie.category.exception.AlreadyExistCategoryException;
import com.movie.category.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCategoryServiceTests {
	@Autowired
	CreateCategoryService createCategoryService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@After
	public void afterTest() {
		Optional<Category> optionalCategory = categoryRepository.findById(new CategoryId("testCategory"));
		optionalCategory.ifPresent(category -> categoryRepository.delete(category));
	}
	
	@Test
	public void createCategory() {
		Long categoryCount = categoryRepository.count();
		Category category1 = createCategoryService.createCategory("testCategory1");
		assertThat(categoryRepository.count()).isEqualTo(categoryCount + 1);
		
		Category category2 = createCategoryService.createCategory("testCategory2");
		assertThat(categoryRepository.count()).isEqualTo(categoryCount + 2);
		
		categoryRepository.delete(category1);
		categoryRepository.delete(category2);
	}
	
	@Test(expected = AlreadyExistCategoryException.class)
	public void duplicateCategory() {
		createCategoryService.createCategory("testCategory");
		createCategoryService.createCategory("testCategory");
	}
}
