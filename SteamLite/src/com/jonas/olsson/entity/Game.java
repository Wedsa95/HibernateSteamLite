package com.jonas.olsson.entity;

import java.util.List;

public class Game {
	
	@Id
	@Column(name="game_id")
	private int gameId;
	
	@Column(name="game_name")
	private String gameName;
	
	@Column(name="game_app_id")
	private int gameAppId;
	
	private Rating rating;
	
	private List<Category> categories;
	
	private List<Achievment> Achivments;
	
	private Game() {
		
	}
	 
}
