package com.jonas.olsson.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = -6044766768034805748L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@OneToOne(mappedBy = "libraryOwner")
	private Library library;
	
	@OneToMany(mappedBy="userState")
	private List<AchievStatus> achievStatuses;
	
	@OneToMany(mappedBy="ratingCritic")
	private List<Rating> ratings;
	
	public User() {
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		return false;
	}
	
	
}
