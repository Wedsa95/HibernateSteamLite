package com.jonas.olsson.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="ratings")
public class Rating implements Serializable{
	
	private static final long serialVersionUID = -7357723153107822181L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rating_id")
	private int ratingId;
	
	@ManyToOne
	@JoinColumn(name = "critic") 
	private User ratingCritic;
	
	@ManyToOne
	@JoinColumn(name = "subject") 
	private Game ratingSubject;
	
	@Column(name = "rating_value")
	private byte ratingValue;
	
	public Rating() {
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		return false;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public User getRatingCritic() {
		return ratingCritic;
	}

	public void setRatingCritic(User ratingCritic) {
		this.ratingCritic = ratingCritic;
	}

	public Game getRatingSubject() {
		return ratingSubject;
	}

	public void setRatingSubject(Game ratingSubject) {
		this.ratingSubject = ratingSubject;
	}

	public byte getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(byte ratingValue) {
		this.ratingValue = ratingValue;
	}
	
}
