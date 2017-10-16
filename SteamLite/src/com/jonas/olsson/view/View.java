package com.jonas.olsson.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Steamliterule.fxml"));
		primaryStage.setTitle("Steam Lite");
		/*
		GridPane pane = new GridPane();
		primaryStage.setTitle("SteamLite");
		System.out.println("in start");
		*/
		
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add("com//jonas//olsson//view//steamlitestyle.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
