package com.jonas.olsson.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public class Game {
	
	@Id
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
	 
}
