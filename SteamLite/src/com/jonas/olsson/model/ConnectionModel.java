package com.jonas.olsson.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.User;



public class ConnectionModel {

	private int curentUser;
	
	private SessionFactory sessionFactory;
	private Session session;
	private ObjectDao userDao;
	
	private User user;

			
	public ConnectionModel(int curentUser) {
		this.curentUser = curentUser;
		sessionFactory = ConnectionSingelton.getInstance().getSessionFactory();
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
