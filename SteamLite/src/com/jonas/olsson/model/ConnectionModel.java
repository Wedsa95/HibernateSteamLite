package com.jonas.olsson.model;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;



public class ConnectionModel {

	private int curentUser;
	
	private SessionFactory sessionFactory;
	private Session session;
	private User user;
	
	final private int[] ratingValues = {1,2,3,4};

			
	public ConnectionModel(int curentUser) {
		
		this.curentUser = curentUser;
		sessionFactory = ConnectionSingelton.getInstance().getSessionFactory();
		System.out.println("in conection con");
		readEntireUser();
		/*
		userDao = new ObjectDao();
		User userOb = new User();
		userOb.setUserName("FapQueen");
		userOb.setUserPassword("fap");
		userOb.setUserEmail("Queen@Mail.com");
		userDao.createObject(userOb);
		*/
	}
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
	public boolean ratingIfExists(Game game,User user) {
		openSessionFlow();
		
			Query query = session.createQuery("from Rating E "
				+ "where E.ratingCritic = :userId and  E.ratingSubject = :gameId");
			query.setParameter("userId", user);
			query.setParameter("gameId", game);
		closeSessionFlow();
		return (query != null);
	}
	public Rating getUserRatingOfGame(Game game,User user) {
		Object rating = null;
		
		openSessionFlow();
		
			Query query = session.createQuery("from Rating E "
					+ "where E.ratingCritic = :userId and  E.ratingSubject = :gameId");
			query.setParameter("userId", user);
			query.setParameter("gameId", game);
		
			try {
				rating = query.getSingleResult();
			}catch(HibernateException e){
				e.printStackTrace();
			}
			
				if (rating == null) {
					Rating dummyRating = new Rating();
					dummyRating.setRatingValue(1); 
					rating = dummyRating;
				}
			
		closeSessionFlow();
		return (Rating) rating;
	}
	
	public void saveRatingForGame(Integer value, Game game) {
		
		Rating tempRating = getUserRatingOfGame(game,user);
		
		if(tempRating.getRatingValue() == 0) {
			openSessionFlow();
		
			Rating rating = tempRating;
			
				rating.setRatingCritic(user);
				rating.setRatingSubject(game);
				rating.setRatingValue(value);
				
				session.update(rating);
			
			closeSessionFlow();
		}else {
			openSessionFlow();
			
			Rating rating = new Rating();
			
				rating.setRatingCritic(user);
				rating.setRatingSubject(game);
				rating.setRatingValue(value);
				
				session.save(rating);
			
			closeSessionFlow();
		}
		
	}
	
	public void updateUserInfo(String name, String email, String password) {
		openSessionFlow();
		
		User user = session.get(User.class, curentUser);
		
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPassword(password);
		
		session.update(user);
		
		closeSessionFlow();
		
	}
	
	public List<Game> getAllGames(){
		openSessionFlow();
			List<?> games = session.createQuery("from Game").getResultList();
		closeSessionFlow();
		
		return (List<Game>) games;
	}
	
	public void addNewGameToCurrentUser(Game game) {
		openSessionFlow();
			User user = session.get(User.class, curentUser);
			
			user.getLibrary().addGameToLibrary(game);
			session.save(user);
		closeSessionFlow();
	}
	public void openSessionFlow() {
		this.session = sessionFactory.openSession();
		this.session.beginTransaction();	
	}
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
