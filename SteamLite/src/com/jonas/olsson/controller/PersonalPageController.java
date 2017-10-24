package com.jonas.olsson.controller;



import java.io.IOException;

import com.jonas.olsson.model.ConnectionModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	public Button uppdateButton;

	public PersonalPageController() {
		model = new ConnectionModel(1);
		
		
	}
	public void initialize() {
		System.out.println("In Initalize Personal");
		//BRYT
		userNameField.setText(model.getUser().getUserName());
		emailField.setText(model.getUser().getUserEmail());
		passwordField.setText(model.getUser().getUserPassword());
		
		uppdateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String name = userNameField.getText().trim();
				String email = emailField.getText().trim();
				String password = passwordField.getText().trim();
				
				model.updateUserInfo(name,email,password);
			}
		});
		//UT
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

}
