package com.jonas.olsson.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Rating {
	
	@Id
	@Column(name="rating_id")
	private int ratingId;
	
	@Column(name="user_id")
	private User critic;
	
	@Column(name="game_id")
	private Game subject;
	
	@Column(name="rating_value")
	private byte ratingValue;
	
	public Rating() {
	}
}
