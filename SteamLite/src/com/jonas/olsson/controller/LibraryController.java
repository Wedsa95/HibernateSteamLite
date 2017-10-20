package com.jonas.olsson.controller;

import java.util.List;

import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.User;
import com.jonas.olsson.model.ConnectionModel;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LibraryController {
	
	private List<Game> games;
	public ConnectionModel model;
	@FXML
	public ListView<Game> gameListView;
	

	public LibraryController() {
		model = new ConnectionModel(1);
		System.out.println("In Controller");
		
	}
	public void initialize() {
		model.openSessionFlow();
		System.out.println("In Initalize");
		
		User user = model.getUserInformation();
		games = user.getLibrary().getGames();
		
		games.forEach(e -> System.out.println(e.getGameName()));
		
		model.closeSessionFlow();
	}


}
