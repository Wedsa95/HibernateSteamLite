package com.jonas.olsson.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jonas.olsson.connection.ConnectionSingelton;
import com.jonas.olsson.entity.User;



public class ConnectionModel {

	private int curentUser;
	private SessionFactory sessionFactory;
	private Session session;
			
	public ConnectionModel(int curentUser) {
		this.curentUser = curentUser;
		sessionFactory = ConnectionSingelton.getInstance().getSessionFactory();
	}
	
	public User getUserInformation() {
		return session.get(User.class, curentUser);
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
