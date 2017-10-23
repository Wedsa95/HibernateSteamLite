package com.jonas.olsson.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;



public class ConnectionModel {

	private int curentUser;
	
	private SessionFactory sessionFactory;
	private Session session;
	//private ObjectDao userDao;
	
	private User user;

			
	public ConnectionModel(int curentUser) {
		this.curentUser = curentUser;
		sessionFactory = ConnectionSingelton.getInstance().getSessionFactory();
		System.out.println("in conection con");
		readEntireUse();
		
	}
	public void readEntireUse() {
		openSessionFlow();
		
			user = session.get(User.class, curentUser);
			user.getAchievStatuses();
			user.getLibrary();
			user.getLibrary().getGames();
			user.getRatings();
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getAchivments());
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getRating());
			user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getCategories());
			
		closeSessionFlow();
	}
	
	public Rating getUserRatingOfGame(Game game,User user) {
		Rating rating = null;
		
		openSessionFlow();
		
			Query<?> query = session.createQuery("from Rating E "
					+ "where E.ratingCritic = :userId and  E.ratingSubject = :gameId");
			query.setParameter("userId", user.getUserId());
			query.setParameter("gameId", game.getGameId());
			
			rating = (Rating) query.getSingleResult();
			if (rating.equals(null)) {
				System.out.println("null");
			}
		closeSessionFlow();
		return rating;
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
}
