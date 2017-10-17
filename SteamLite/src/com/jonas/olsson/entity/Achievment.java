package com.jonas.olsson.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="achievments")
public class Achievment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664299405716442198L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="achiev_id")
	private int achievId;
	
	@Column(name="achiev_name")
	private String achievName;
	
	@Column(name="achiev_point")
	private int achievPoint;
	
	public Achievment() {
	}
}
