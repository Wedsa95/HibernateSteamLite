package com.jonas.olsson.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Category {
	
	@Id
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_id")
	private String categoryName;

	
	@ManyToMany(fetch=FetchType.LAZY, cascade= 
		{CascadeType.PERSIST, CascadeType.MERGE
		,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="categories_for_games",
		joinColumns=@JoinColumn(name="categories_for"),
		inverseJoinColumns=@JoinColumn(name="for_games"))
	private List<Game> theGame;
	
	public Category() {
	}
}
