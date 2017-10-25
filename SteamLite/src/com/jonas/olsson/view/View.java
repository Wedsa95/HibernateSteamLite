package com.jonas.olsson.view;

import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application{
	
	private Stage primaryStage;
	/**
	 * Stars the {@link Stage} in javafx.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent library = FXMLLoader.load(getClass().getResource("Library.fxml"));
	
		Scene scene = new Scene(library, 600, 600);
		
		primaryStage.setTitle("Steam Lite");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * An method that allows the user to traverse
	 * {@link Scene}'s
	 * 
	 * @param fxml An String of a path from src Folder
	 * @throws Exception If path is incorrect
	 */
	public void changeScene(String fxml) throws Exception{
	    Parent pane = FXMLLoader.load(
	           getClass().getResource(fxml));

	   Scene scene = new Scene( pane );
	   primaryStage.setScene(scene);
	   primaryStage.show();
	}
	/**
	 * Override stop method form the javafx package
	 * that end operations extending from {@link Application}.
	 */
	@Override
	public void stop() throws Exception {
		SessionFactory stop = ConnectionSingelton.getInstance().getSessionFactory();
		stop.close();
	}
	public static void main(String [] args) {
		launch(View.class,args);
	}	
}
