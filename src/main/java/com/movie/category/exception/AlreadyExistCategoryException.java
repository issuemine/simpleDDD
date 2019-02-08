package com.movie.category.exception;

public class AlreadyExistCategoryException extends RuntimeException {
	private String category;
	
	public AlreadyExistCategoryException(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
}
