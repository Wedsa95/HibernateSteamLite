package com.jonas.olsson.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.jonas.olsson.entity.Game;
import com.jonas.olsson.model.ConnectionModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;

public class LibraryController {
	

	public ConnectionModel model;
	
	@FXML
	public ListView<Game> gameListView;
	
	@FXML
	public MenuButton ratingButton;
	
	@FXML
	public MenuButton categoryButton;
	
	@FXML
	public Label gameNameLabel;
	
	@FXML
	public Button playButton;
	

	public LibraryController() {
		model = new ConnectionModel(1);
		System.out.println("In Controller");
		
	}
	public void initialize() {
		
		System.out.println("In Initalize");
		model.readInUserInformation();
		startGameListView();
		
		
		
	}
	
	private void startGameListView() {
		
		gameListView.getItems().setAll(model.getUser().getLibrary().getGames());
		gameListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		gameListView.getSelectionModel().selectFirst();	
		
	
		gameNameLabel.setText(model.getUser().getLibrary().getGames()
				.get(gameListView.getSelectionModel().getSelectedIndex()).getGameName());
		
		ratingButton.setText("rating");
		
		gameListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {
			@Override
			public void changed(ObservableValue<? extends Game> observable, Game oldValue, Game newValue) {
				if(newValue != null) {
					int game = gameListView.getSelectionModel().getSelectedIndex();
					
					gameNameLabel.setText(model.getUser().getLibrary().getGames().get(game).getGameName());
					//ratingButton.setText(model.getUser().getLibrary().getGames().get(game).getGameName());
					
				}
			
			}
		});
		
		
	}
	
	private void updateGameListView() {
			
	}
	
	
	
	
	@FXML
	private void showOptions() {
		
	}
	
	@FXML
	private void closeOptions() {
		
	}
	/*
	private void playGame() {
		try {
			Desktop.getDesktop().browse(new URI("steam://runsafe/"+model.getGames().get(
					gameListView.getSelectionModel().getSelectedIndex()).getGameAppId()));
			
		}catch(IOException e){
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
}
