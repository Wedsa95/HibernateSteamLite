package com.jonas.olsson.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

public class Library {
	
	@Id
	@Column(name="library_id")
	private int libraryId;
	
	@Column(name="user_id")
	private int owner;
	
	@Column(name="have_games")
	private List<Game> games;
	
	public Library() {
	}
}
