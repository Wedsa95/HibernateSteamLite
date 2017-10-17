package com.jonas.olsson.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="libraries")
public class Library implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2911423518982507442L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="library_id")
	private int libraryId;
	
	@Column(name="user_id")
	private int owner;
	
	@OneToMany(mappedBy="have_games", cascade= 
			{CascadeType.PERSIST, CascadeType.MERGE
			,CascadeType.DETACH, CascadeType.REFRESH})
	private List<Game> games;
	
	public Library() {
	}
	
	public void addGameToLibrary(Game game) {
		if(games == null) {
			games = new ArrayList<>();
		}
		games.add(game);
		
		//game.setBelongTo(this);
	}
}
