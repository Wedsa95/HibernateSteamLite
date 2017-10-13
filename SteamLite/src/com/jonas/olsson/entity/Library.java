package com.jonas.olsson.entity;

import java.util.List;

public class Library {
	
	@Id
	@Column(name="library_id")
	private int libraryId;
	private List<Game> games;
	
	public Library() {
	}
}
