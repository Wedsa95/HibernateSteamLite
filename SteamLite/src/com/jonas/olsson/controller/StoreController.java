package com.jonas.olsson.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
	
	ArrayList<VBox> boxes = new ArrayList<VBox>();
	
	@FXML
	public FlowPane flowPane;
	@FXML
	public Button yourPageButton;
	@FXML
	public Button libraryButton;
	@FXML
	public Button storeButton;


	public StoreController() {
		model = new ConnectionModel(1);
		System.out.println("In Controller");
		
	}
	public void initialize() {
		System.out.println("In Initalize Store");
		List<Game> games= model.getAllGames();
		
		VBox pane1 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane2 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane3 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane4 = createItemPane(games.get((int) (Math.random()*11)));
		VBox pane5 = createItemPane(games.get((int) (Math.random()*11)));
		boxes.add(pane1);
		boxes.add(pane2);
		boxes.add(pane3);
		boxes.add(pane4);
		boxes.add(pane5);

		flowPane.getChildren().addAll(boxes);
	}
	@FXML
	 private void switchSceneButtonAction(ActionEvent event) throws IOException{
	     Stage stage; 
	     Parent root;
	     
	     if(event.getSource() == libraryButton){
	    	 //get reference to the button's stage         
	    	 stage=(Stage) libraryButton.getScene().getWindow();
	    	 //load up OTHER FXML document
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Library.fxml"));
	    	 System.out.println(root);
	    	 
	      }else if(event.getSource() == storeButton){
	    	 stage=(Stage) storeButton.getScene().getWindow();
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Store.fxml"));
	    	 System.out.println(root);
	    	 
	      }else {
	    	  stage=(Stage) yourPageButton.getScene().getWindow();
	    	  root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/PersonalPage.fxml"));
	    	  System.out.println(root);
	      }
	     	//create a new scene with root and set the stage
	     	Scene scene = new Scene(root);
	     	stage.setScene(scene);
	     	stage.show();
	}
	private VBox createItemPane(Game game) {
		Game gamez = game;
		VBox pane = new VBox(10);
		pane.setMinHeight(150);
		pane.setMinWidth(150);
		Label gameName = new Label(game.getGameName());
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
