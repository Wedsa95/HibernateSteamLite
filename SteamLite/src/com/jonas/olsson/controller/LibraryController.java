package com.jonas.olsson.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.jonas.olsson.entity.Category;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;
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
import javafx.scene.control.ChoiceBox;
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
	public ChoiceBox<Integer> ratingButton;
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

	/**
	 * An constructor that creates a object
	 * of the {@link ConnectionModel}
	 * 
	 * @see	ConnectionModel 
	 */
	public LibraryController() {
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
		System.out.println("In Initalize");
		setupGameListView();
		setPlayButtonActionListener();
		setupRatingButton();
		setRatingButtonActionListener();

	}

	/**
	 * Adds a {@link EventHandler} to the
	 * ratingsButton 
	 */
	private void setRatingButtonActionListener() {
		ratingButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean ratingExists = model.ratingIfExists
						(gameListView.getSelectionModel().getSelectedItem(), model.getUser());

				if(ratingExists) {

					Rating rating = model.getUserRatingOfGame(model.getUser().getLibrary().getGames()
							.get(gameListView.getSelectionModel().getSelectedIndex()), model.getUser());

					model.updateRatingForGame(ratingButton.getValue(),rating);
				}else {

					model.saveRatingForGame(ratingButton.getValue(),
							model.getUser().getLibrary().getGames()
							.get(gameListView.getSelectionModel().getSelectedIndex()));
				}

			}
		});
	}
	/**
	 * This method controls most of the components
	 * in the on the Library.fxml scene. 
	 */
	private void setupGameListView() {

		addItemsToListView();
		gameListView.getSelectionModel().selectFirst();
		gameListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if (model.getUser().getLibrary().getGames().isEmpty()) {
			updateGameNameLabel("");
		}else {
			updateGameNameLabel(model.getUser().getLibrary().getGames()
				.get(gameListView.getSelectionModel().getSelectedIndex()).getGameName());
		}
		

		gameListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {

			@Override
			public void changed(ObservableValue<? extends Game> observable, Game oldValue, Game newValue) {
				if(newValue != null) {

					model.readEntireUser();

					int gameIndex = gameListView.getSelectionModel().getSelectedIndex();
					categoryButton.getItems().clear();

					updateGameNameLabel(model.getUser().getLibrary().getGames().get(gameIndex).getGameName());

					updateRatingButton(model.getUser().getLibrary().getGames().get(gameIndex),model.getUser());

					updateCategoyButton(gameIndex);

				}
			}
		});
	}

	private void addItemsToListView() {
		model.readEntireUser();
		List<Game> games = model.getUser().getLibrary().getGames();
		if (!games.isEmpty()) {
			gameListView.getItems().setAll(games);
		}else {
			List<Game> dummyGameList = new ArrayList<>();
			Game dummyGame = new Game();
			dummyGameList.add(dummyGame);
		}
	}
	
	/**
	 * Takes in a int that decides the current
	 * games category's to show.
	 * 
	 * @param gameIndex
	 */
	private void updateCategoyButton(int gameIndex) {
		//categoryButton.getItems().add(new MenuItem("Cattt"));

		List<Category> cat = model.getUser().getLibrary().getGames().get(gameIndex).getCategories();
		for(Category category : cat) {
			System.out.println("GETING cat");
			categoryButton.getItems().add(new MenuItem(category.getCategoryName()));
		}
	}

	/**
	 * Takes in a String that will be displayed
	 * on the gameNameLabel.
	 * 
	 * @param gameName	
	 */
	private void updateGameNameLabel(String gameName) {
		gameNameLabel.setText(gameName);
	}

	/**
	 * Takes in a Game and User entity and 
	 * uses the method's in the {@link ConnectionModel}
	 * to determine what value to show on the button.
	 * 
	 * @param game	
	 * @param user
	 */
	private void updateRatingButton(Game game, User user){
		boolean ratingExists = model.ratingIfExists
				(gameListView.getSelectionModel().getSelectedItem(), model.getUser());
			ratingButton.setValue(null);
		if(ratingExists) {
			Rating rating = model.getUserRatingOfGame(game, user);
			ratingButton.setValue(rating.getRatingValue());
		}else {
			ratingButton.setValue(null);
		}
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

	/**
	 * Instantiates a other program based
	 * on the in parameter. 
	 * 
	 * @param game
	 */
	private void playGame(Game game) {
		try {
			Desktop.getDesktop().browse(new URI("steam://runsafe/"+game.getGameAppId()));

		}catch(IOException e){

			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets up the ratingButton with
	 * it's values
	 */
	private void setupRatingButton() {
		int[] values = model.getRatingValues();

		for(int i = 0; i < values.length; ++i) {
			ratingButton.getItems().add(i,values[i]);
		}
	}
	/**
	 * Sets a {@link EventHandler} on the 
	 * playButton
	 * 
	 * @see	Button
	 */
	private void setPlayButtonActionListener() {
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playGame(gameListView.getSelectionModel().getSelectedItem());
			}
		});
	}
}
