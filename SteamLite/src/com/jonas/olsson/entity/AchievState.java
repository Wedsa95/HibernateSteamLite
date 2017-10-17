package com.jonas.olsson.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user_have_achiev")
public class AchievState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1127893160374144540L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_have_achiev")
	private int id;
	
	@Column(name="user_have")
	private User user;
	
	@Column(name="have_achiev")
	private Achievment achiev;
	
	@Column(name="achiev_unlock")
	private boolean achievUnlocked;
}
