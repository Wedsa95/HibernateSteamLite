package com.jonas.olsson.model;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Library;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;



public class ConnectionModel {

	private int curentUser;
	private User user;
	
	private SessionFactory sessionFactory;
	private Session session;

	final private int[] ratingValues = {1,2,3,4};

	/**
	 * Constructor takes in the id of the 
	 * current user and uses it find the users
	 * data in the database.
	 * 	
	 * @param curentUser
	 */
	public ConnectionModel(int curentUser) {
		
		this.curentUser = curentUser;
		sessionFactory = ConnectionSingelton.getInstance().getSessionFactory();
		System.out.println("in conection con");
		readEntireUser();
	}
	/**
	 * Reads in to memory all of the related tables
	 * of the current user.
	 * 
	 * @see Hibernate
	 */
	public void readEntireUser() {
		openSessionFlow();
		
			user = session.get(User.class, curentUser);
			user.getAchievStatuses();
			user.getLibrary();
			user.getRatings();
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getAchivments());
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getRating());
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getCategories());
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getCategories());
			
		closeSessionFlow();
	}
	/**
	 * Returns a boolean based on the in parameters
	 * game, user. It searches for the matching 
	 * Rating tuple in the database.
	 * If it does exists it returns true, if not false.
	 * 
	 * @param game	an game the user want search for
	 * @param user	the current user
	 * @return boolean	
	 */
	public boolean ratingIfExists(Game game,User user) {
		Rating rating = null;
		openSessionFlow();
		try {
			CriteriaBuilder build = session.getCriteriaBuilder();
			CriteriaQuery<Rating> crit = build.createQuery(Rating.class);
		
			Root<Rating> rat = crit.from(Rating.class); 
			crit.where(rat.get("ratingCritic").in(user.getUserId()))
				.where(rat.get("ratingSubject").in(game.getGameId()));
		
			rating = session.createQuery(crit).getSingleResult();
		}catch (PersistenceException e){
			e.printStackTrace();
		}
		closeSessionFlow();
		return (rating != null); 
	}
	/**
	 * Returns a Rating bases on the in parameter 
	 * game, user in the database.
	 * If this Rating do not exist it will have a 
	 * hissy fit and return nothing.  
	 * 
	 * @param game	an game the user want search for
	 * @param user	the current user
	 * @return Rating	an entity that the user created
	 */
	public Rating getUserRatingOfGame(Game game,User user) {
		Object rating = null;
		openSessionFlow();
			try {
				Query<?> query = session.createQuery("from Rating E "
						+ "where E.ratingCritic = :userId and  E.ratingSubject = :gameId");
					query.setParameter("userId", user);
					query.setParameter("gameId", game);
		
			
				rating = query.getSingleResult();
			
			}catch(HibernateException e){
				e.printStackTrace();
			}
			
		closeSessionFlow();
		return (Rating) rating;
	}
	/**
	 * Takes in a Integer value that, the user wants
	 * to put on a game. 
	 * The method creates a new {@link Rating} and
	 * links it to the current user and it's list of games. 
	 * 
	 * @param value	an value between 1-4
	 * @param game	the Game entity the user wants to rate
	 */
	public void saveRatingForGame(Integer value, Game game) {
			openSessionFlow();
		
			Rating rating = new Rating();
				rating.setRatingCritic(user);
				rating.setRatingSubject(game);
				rating.setRatingValue((int)value);
				
				session.save(rating);
			
			closeSessionFlow();
	}
	/**
	 * Takes in the new Integer value the user wants
	 * to set on the a already existing database 
	 * tuple.
	 * 
	 * @param value	an value between 1-4
	 * @param rating	the Rating entity to be changed
	 */
	public void updateRatingForGame(Integer value, Rating rating) {

			openSessionFlow();
		
			Rating tempRating = session.get(Rating.class, rating.getRatingId());
			
			tempRating.setRatingValue(value);
				
			session.update(tempRating);
			
			closeSessionFlow();
	}
	/**
	 * Takes in name, email, password 
	 * and updates the information in the current 
	 * users database tuple.
	 * 
	 * @param name	also called userName in User entity
	 * @param email	also called userEmail in User entity
	 * @param password	also called userPassword in User entity
	 */
	public void updateUserInfo(String name, String email, String password) {
		openSessionFlow();
		
		User user = session.get(User.class, curentUser);
		
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPassword(password);
		
		session.update(user);
		
		closeSessionFlow();
		
	}
	/**
	 * Returns a full list of all games
	 * in the database.
	 * 
	 * @return List<Game> an absolute list 
	 * of all games in the database
	 */
	public List<Game> getAllGames(){
		openSessionFlow();
			List<?> games = session.createQuery("from Game").getResultList();
		closeSessionFlow();
		
		return (List<Game>) games;
	}
	/**
	 * Takes in a Game entity 
	 * and stores it to the database.
	 * 
	 * @param game 
	 */
	public void addNewGameToCurrentUser(Game game) {
		openSessionFlow();
			User user = session.get(User.class, curentUser);
			
			user.getLibrary().addGameToLibrary(game);
			session.save(user);
		closeSessionFlow();
	}
	
	public void deleteLibraryOfCurrentUser() {
		
		openSessionFlow();	
			try{
				Library library = session.get(Library.class, user.getLibrary().getLibraryId());
				library.getGames().clear();
			}catch(HibernateException e) {
				e.printStackTrace();
			}
		
		closeSessionFlow();
	}
	public void deleteRatingsOfCurrentUser() {
		openSessionFlow();
		
		closeSessionFlow();
	}
	/**
	 * Creates and opens a hibernate session. 
	 * Using the {@link ConnectionSingelton} 
	 * sessionFactory.
	 * This allows the retrieval of information
	 * from the database.
	 */
	public void openSessionFlow() {
		this.session = sessionFactory.openSession();
		this.session.beginTransaction();	
	}
	/**
	 * Completes the transaction to the database
	 * and closes the hibernate session. 
	 */
	public void closeSessionFlow() {
		this.session.getTransaction().commit();
		this.session.close();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCurentUser() {
		return curentUser;
	}
	public void setCurentUser(int curentUser) {
		this.curentUser = curentUser;
	}
	public int[] getRatingValues() {
		return ratingValues;
	}
	
}
