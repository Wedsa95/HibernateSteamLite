package com.jonas.olsson.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jonas.olsson.entity.Achievment;
import com.jonas.olsson.entity.Category;
import com.jonas.olsson.entity.Game;
import com.jonas.olsson.entity.Library;
import com.jonas.olsson.entity.Rating;
import com.jonas.olsson.entity.User;

public class ConnectionSingelton {


	private static ConnectionSingelton instance = new ConnectionSingelton();
	private SessionFactory sessionFactory;

	private ConnectionSingelton(){
		this.sessionFactory = buildSessionFactory();
	}

	private SessionFactory buildSessionFactory() {
		return new Configuration().configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(Library.class)
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(Achievment.class)
				.buildSessionFactory();
	}

	public static ConnectionSingelton getInstance() {
		if(instance == null){
			return new ConnectionSingelton();
		}
		return instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}