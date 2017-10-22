package com.jonas.olsson.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.User;

public class ObjectDao {
	
	private SessionFactory sessionFactory = 
			ConnectionSingelton.getInstance().getSessionFactory();
	private Session session;
	
	
	public List<?> readAllFromObject() {
		openSessionFlow();
		List<?> users = session.createQuery("from users").getResultList();	
		closeSessionFlow();
		return users;
	}
	public User readUserById(int id) {
		openSessionFlow();
		User user = session.get(User.class, id);
		System.out.println(user.getUserEmail());
		closeSessionFlow();
		return user;
	}
	
	public void createObject(Object ob) {
		openSessionFlow();
		session.save(ob);
		closeSessionFlow();
	}
	public void updateObject(Object ob) {
		openSessionFlow();
		session.update(ob);
		closeSessionFlow();
	}
	public void deleteObject(Object ob) {
		openSessionFlow();
		session.delete(ob);
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
