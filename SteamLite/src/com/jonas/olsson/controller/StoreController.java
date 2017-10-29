package com.jonas.olsson.controller;

import java.io.IOException;
import java.util.List;

import com.jonas.olsson.entity.Game;
import com.jonas.olsson.model.ConnectionModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StoreController {
	
	public ConnectionModel model;
	
	@FXML
	public FlowPane flowPane;
	@FXML
	public Button yourPageButton;
	@FXML
	public Button libraryButton;
	@FXML
	public Button storeButton;
	/**
	 * An constructor that creates a object
	 * of the {@link ConnectionModel}
	 * 
	 * @see	ConnectionModel 
	 */
	public StoreController() {
		model = new ConnectionModel(1);
	}
	
	/**
	 * an method that the main automatically 
	 * calls for while using the javafx.application
	 * package 
	 * 
	 * @see javafx.fxml
	 */
	public void initialize() {
		List<Game> games= model.getAllGames();
		setupStoreItemPanes(games);
	}
	/**
	 * Creates a number of createItemPane 
	 * layouts. Then adds the it to the center 
	 * of the root pane
	 * 
	 * @param games	
	 * @see Store.fxml
	 */
	private void setupStoreItemPanes(List<Game> games) {
		
		
		VBox pane1 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane2 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane3 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane4 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane5 = createItemPane(games.get((int) (Math.random()*11)));

		flowPane.getChildren().addAll(pane1 ,pane2 ,pane3 ,pane4 ,pane5);
	}
	/**
	 * This is a action event that switch the {@link Scene}
	 * 
	 * @param event	uses it to determine what button was pressed 
	 * @throws IOException When no fxml file was found
	 * @see Stage
	 */
	@FXML
	private void switchSceneButtonAction(ActionEvent event) throws IOException{
	     Stage stage; 
	     Parent root;
	     
	     if(event.getSource() == libraryButton){
	    	      
	    	 stage=(Stage) libraryButton.getScene().getWindow();
	    	
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Library.fxml"));
	    	 System.out.println(root);
	    	 
	      }else if(event.getSource() == storeButton){
	    	 stage=(Stage) storeButton.getScene().getWindow();
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Store.fxml"));
	    	 System.out.println(root);
	    	 
	      }else {
	    	  stage=(Stage) yourPageButton.getScene().getWindow();
	    	  root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/PersonalPage.fxml"));
	   
	      }
	    
	     	Scene scene = new Scene(root);
	     	stage.setScene(scene);
	     	stage.show();
	}
	/**
	 * Takes in a Game entity and it decides some of the 
	 * look of the layout.
	 * 
	 * Returns a VBox layout with a {@link Button} 
	 * and {@link Label}. Then adds the gameName to the 
	 * Label and {@link EventHandler} to the Button.
	 * 
	 * @param game	The Game entity
	 * @return VBox	A layout with additional controllers on
	 * @see VBox javafx.con
	 */
	private VBox createItemPane(Game game) {
		Game gamez = game;
		VBox pane = new VBox(10);
		pane.setMinHeight(150);
		pane.setMinWidth(150);
		Label gameName = new Label(game.getGameName());
		gameName.wrapTextProperty();
		Button buyButton = new Button("Buy");
		buyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.addNewGameToCurrentUser(gamez);
				
			}
		});
		pane.getChildren().setAll(gameName,buyButton);
		return pane;
	}
}
