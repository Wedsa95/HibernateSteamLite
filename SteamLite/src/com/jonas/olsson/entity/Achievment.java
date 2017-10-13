package com.jonas.olsson.entity;

public class Achievment {

	@Id
	@Column(name="achiev_id")
	private int achievId;
	
	@Column(name="achiev_name")
	private String achievName;
	
	@Column(name="achiev_point")
	private byte achievPoint;
	
	public Achievment() {
	}
}
