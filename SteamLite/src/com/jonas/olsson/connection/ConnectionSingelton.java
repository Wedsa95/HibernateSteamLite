package com.jonas.olsson.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jonas.olsson.entity.AchievStatus;
import com.jonas.olsson.entity.Achievment;
import com.jonas.olsson.entity.Category;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Library;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;

public class ConnectionSingelton {


	private static ConnectionSingelton instance = new ConnectionSingelton();
	private SessionFactory sessionFactory;
	
	/**
	 * An private constructor that creates
	 * only one instance of a {@link SessionFactory}
	 * @see Hibernate documentation 
	 */
	private ConnectionSingelton(){
		this.sessionFactory = buildSessionFactory();
	}
	
	/**
	 * An private method that returns a configured
	 * and entity inserted {@link SessionFactory}
	 * 
	 * @return SessionFactory 
	 * @see Hibernate documentation 
	 */
	private SessionFactory buildSessionFactory() {
		return new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(Library.class)
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(AchievStatus.class)
				.addAnnotatedClass(Achievment.class)
				.buildSessionFactory();
	}
	
	/**
	 * An static method that instantiate the 
	 * constructor only ones. And returns the 
	 * {@link ConnectionSingelton}
	 * 
	 * @return ConnectionSingelton 
	 * @see Hibernate documentation 
	 */
	public static ConnectionSingelton getInstance() {
		if(instance == null){
			return new ConnectionSingelton();
		}
		return instance;
	}
	/**
	 * Returns the one and only {@link SessionFactory} 
	 * object that is used to communicate to database.
	 * 
	 * @return sessionFactory 
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * Closes the {@link SessionFactory}.
	 * And there by the link to the database.
	 * 
	 * @see Hibernate documentation 
	 */
	public void closeConnection() {
		sessionFactory.close();
	}

}