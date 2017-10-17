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
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="games")
public class Game implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5958958094046147635L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="game_id")
	private int gameId;
	
	@Column(name="game_name")
	private String gameName;
	
	@Column(name="game_app_id")
	private int gameAppId;
	
	
	@OneToMany(mappedBy="subject", cascade= 
		{CascadeType.PERSIST, CascadeType.MERGE
		,CascadeType.DETACH, CascadeType.REFRESH})
	private List<Rating> rating;
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= 
		{CascadeType.PERSIST, CascadeType.MERGE
		,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="categories_for_games",
		joinColumns=@JoinColumn(name="for_games"),
		inverseJoinColumns=@JoinColumn(name="categories_for"))
	private List<Category> categories;
	
	
	@OneToMany(mappedBy="game_with_achiev", cascade= 
		{CascadeType.PERSIST, CascadeType.MERGE
		,CascadeType.DETACH, CascadeType.REFRESH})
	private List<Achievment> Achivments;
	
	private Game() {
		
	}
	public void addCategoryToGames(Category category) {
		if(categories == null) {
			categories = new ArrayList<>();
		}
		categories.add(category);
	}
}
