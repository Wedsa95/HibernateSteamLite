package com.jonas.olsson.controller;



import java.io.IOException;

import com.jonas.olsson.model.ConnectionModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonalPageController {
	
	public ConnectionModel model;
	
	@FXML
	public Button yourPageButton;
	@FXML
	public Button libraryButton;
	@FXML
	public Button storeButton;
	@FXML
	public TextField userNameField;
	@FXML
	public TextField emailField;
	@FXML
	public TextField passwordField;
	@FXML
	public Button updateButton;

	/**
	 * An constructor that creates a object
	 * of the {@link ConnectionModel}
	 * 
	 * @see	ConnectionModel 
	 */
	public PersonalPageController() {
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
	
		setupFields();
		setupUpdateButton();
		
	}
	/**
	 * Sets up the updateButtons {@link EventHandler}.
	 */
	private void setupUpdateButton() {
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String name = userNameField.getText().trim();
				String email = emailField.getText().trim();
				String password = passwordField.getText().trim();
				
				model.updateUserInfo(name,email,password);
			}
		});
	}
	/**
	 * Sets up the {@link TextField} and {@link PasswordField}
	 * and adds the current users information from the database.
	 */
	private void setupFields() {

		userNameField.setText(model.getUser().getUserName());
		emailField.setText(model.getUser().getUserEmail());
		passwordField.setText(model.getUser().getUserPassword());
	}
	
	/**
	 * This is a action event that switch the {@link Scene}
	 * 
	 * @param event	uses it to determine what button was pressed 
	 * @throws IOException whhen no fxml file was found
	 * @see Stage
	 */
	@FXML
	private void switchSceneButtonAction(ActionEvent event) throws IOException{
	     Stage stage; 
	     Parent root;
	     
	     if(event.getSource() == libraryButton){
	    	 stage=(Stage) libraryButton.getScene().getWindow();
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Library.fxml"));
	      }else if(event.getSource() == storeButton){
	    	 stage=(Stage) storeButton.getScene().getWindow();
	    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/Store.fxml"));
	      }else {
	    	  stage=(Stage) yourPageButton.getScene().getWindow();
	    	  root = FXMLLoader.load(getClass().getClassLoader().getResource("com/jonas/olsson/view/PersonalPage.fxml"));
	      }
	     	Scene scene = new Scene(root);
	     	stage.setScene(scene);
	     	stage.show();
	}

}
