package com.jonas.olsson.controller;

import com.jonas.olsson.entity.Game;
import com.jonas.olsson.model.ConnectionModel;
import com.jonas.olsson.view.View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
	
	@FXML
	private ListView<Game> gameListView;

	public Controller(ConnectionModel model) {
		
		Application.launch(View.class);
		
	}
	private void initialize() {
		
	}
}
