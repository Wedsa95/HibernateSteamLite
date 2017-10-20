package com.jonas.olsson.controller;



import com.jonas.olsson.model.ConnectionModel;

public class PersonalPageController {
	
	public ConnectionModel model;
	

	public PersonalPageController() {
		model = new ConnectionModel();
		System.out.println("In Controller");
		
	}
	public void initialize() {
		System.out.println("In Initalize Personal");
	}
	

}
