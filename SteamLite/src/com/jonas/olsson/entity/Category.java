package com.jonas.olsson.entity;

public class Category {
	
	@Id
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_id")
	private String categoryName;
	
	public Category() {
	}
}
