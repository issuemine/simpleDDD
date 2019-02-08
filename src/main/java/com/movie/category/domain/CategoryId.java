package com.movie.category.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryId implements Serializable {
	@Column(name = "category_id")
	private String id;
	
	public CategoryId() {}
	
	public CategoryId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
