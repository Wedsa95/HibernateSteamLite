package com.jonas.olsson.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

public class User {

	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="library_have")
	private Library library;
	
	@Column(name="user_have")
	private List<Achievment> achievments;
	
	@Column(name="critic")
	private List<Rating> ratings;
	
	public User() {
	}
	
	
}
