package com.jonas.olsson.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="achievments")
public class Achievment implements Serializable{

	private static final long serialVersionUID = 8664299405716442198L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="achiev_id")
	private int achievId;
	
	@ManyToOne
	@JoinColumn(name="game_with_achiev")
	private Game achievGame;
	
	@Column(name="achiev_name")
	private String achievName;
	
	@Column(name="achiev_point")
	private int achievPoint;
	
	@OneToMany(mappedBy="achievState")
	private List<AchievStatus> achiveStatuses;
	

	
	public Achievment() {
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		return false;
	}
	
}
