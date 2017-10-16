package com.jonas.olsson.entity;

import javax.persistence.Column;
import javax.persistence.Id;

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
