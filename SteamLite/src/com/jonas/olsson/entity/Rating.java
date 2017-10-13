package com.jonas.olsson.entity;

public class Rating {
	
	@Id
	@Column(name="rating_id")
	private int ratingId;
	
	private User critic;
	
	private Game subject;
	
	public Rating() {
	}
}
