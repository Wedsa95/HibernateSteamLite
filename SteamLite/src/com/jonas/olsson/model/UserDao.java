package com.jonas.olsson.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.User;

public class UserDao {
	
	private SessionFactory sessionFactory = 
			ConnectionSingelton.getInstance().getSessionFactory();
	private Session session;
	
	public void createUser(User user) {
		openSessionFlow();
		session.save(user);
		closeSessionFlow();
	}
	public List<?> readAllUser() {
		openSessionFlow();
		List<?> users = session.createQuery("from users").getResultList();	
		closeSessionFlow();
		return users;
	}
	public User readUserById(int id) {
		openSessionFlow();
		User user = session.get(User.class, id);
		System.out.println(user);
		user.getAchievStatuses();
		user.getLibrary();
		user.getRatings();
		user.getLibrary().getGames();
		user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getAchivments());
		user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getCategories());
		user.getLibrary().getGames().iterator().forEachRemaining(e -> e.getRating());
		closeSessionFlow();
		return user;
	}
	public void updateUser(User user) {
		openSessionFlow();
		session.update(user);
		closeSessionFlow();
	}
	public void deleteUser(User user) {
		openSessionFlow();
		session.delete(user);
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
}
