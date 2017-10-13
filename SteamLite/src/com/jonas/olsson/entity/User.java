package com.jonas.olsson.entity;

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
	
	private Library library;
	
	public User() {
	}
	
	
}
