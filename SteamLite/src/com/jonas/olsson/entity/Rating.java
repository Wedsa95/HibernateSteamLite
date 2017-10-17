package com.jonas.olsson.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ratings")
public class Rating implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7357723153107822181L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
