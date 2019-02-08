package com.movie.category.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Category {
	@EmbeddedId
	private CategoryId categoryId;
	
	public Category() {}
	
	public Category(CategoryId categoryId) {
		setCategoryId(categoryId);
	}
	
	public CategoryId getCategoryId() {
		return categoryId;
	}

	private void setCategoryId(CategoryId categoryId) {
		if (categoryId == null) {
			throw new IllegalArgumentException("categoryId");
		}
		this.categoryId = categoryId;
	}
}
