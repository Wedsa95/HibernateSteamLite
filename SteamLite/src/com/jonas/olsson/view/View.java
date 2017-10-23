package com.jonas.olsson.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application{
	
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent library = FXMLLoader.load(getClass().getResource("Library.fxml"));
		//Parent root = loader.load();
	
		Scene scene = new Scene(library, 1200, 800);
		
		primaryStage.setTitle("Steam Lite");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void changeScene(String fxml) throws Exception{
	    Parent pane = FXMLLoader.load(
	           getClass().getResource(fxml));

	   Scene scene = new Scene( pane );
	   primaryStage.setScene(scene);
	}
	public static void main(String [] args) {
		launch(View.class,args);
	}	
}
