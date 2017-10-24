package com.jonas.olsson.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.jonas.olsson.entity.Category;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.model.ConnectionModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

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
	@FXML
	public Button libraryButton;
	@FXML
	public Button storeButton;
	@FXML
	public Button yourPageButton;
	

	public LibraryController() {
		model = new ConnectionModel(1);
		System.out.println("In Controller");
		
	}
	public void initialize() {
		System.out.println("In Initalize");
		startGameListView();
		setPlayButtonActionListener();
		
	}
	
	private void setPlayButtonActionListener() {
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playGame(gameListView.getSelectionModel().getSelectedItem());
			}
		});
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
					
					categoryButton.getItems().clear();
					//BRYT
					int gameIndex = gameListView.getSelectionModel().getSelectedIndex();
					//UT
					
					//BRYT
					gameNameLabel.setText(model.getUser().getLibrary().getGames().get(gameIndex).getGameName());
					//UT
					
					//BRYT
					Rating rating = model.getUserRatingOfGame(gameListView.getSelectionModel().getSelectedItem(),model.getUser());
					
					ratingButton.setText(rating.getRatingValue()+"");
					//UT
					
					List<Category> cat = model.getUser().getLibrary().getGames().get(gameIndex).getCategories();
					for(Category category : cat) {
						System.out.println("GETING cat");
						categoryButton.getItems().add(new MenuItem(category.getCategoryName()));
					}
					
				}
			
			}
		});
		
		
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
	
	private void playGame(Game game) {
		try {
			Desktop.getDesktop().browse(new URI("steam://runsafe/"+game.getGameAppId()));
			
		}catch(IOException e){
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	
}
