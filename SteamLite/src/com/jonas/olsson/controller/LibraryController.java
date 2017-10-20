package com.jonas.olsson.controller;

import com.jonas.olsson.entity.Game;
import com.jonas.olsson.model.ConnectionModel;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LibraryController {
	
	public ConnectionModel model;
	@FXML
	public ListView<Game> gameListView;
	

	public LibraryController() {
		model = new ConnectionModel();
		System.out.println("In Controller");
		
	}
	public void initialize() {
		System.out.println("In Initalize");
	}


}
