package com.jonas.olsson.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8013869256143217956L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	private List<Game> theGames;
	
	public Category() {
	}
	
	public void addGameToCategories(Game game) {
		if(theGames == null) {
			theGames = new ArrayList<>();
		}
		theGames.add(game);
	}
}
