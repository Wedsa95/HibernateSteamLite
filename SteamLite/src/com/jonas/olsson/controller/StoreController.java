package com.jonas.olsson.controller;

import com.jonas.olsson.model.ConnectionModel;

public class StoreController {
	
	public ConnectionModel model;
	

	public StoreController() {
		model = new ConnectionModel();
		System.out.println("In Controller");
		
	}
	public void initialize() {
		System.out.println("In Initalize Store");
	}
	
	

}
