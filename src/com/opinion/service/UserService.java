package com.opinion.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinion.model.User;
import com.opinion.model.UserHome;

@Service
public class UserService {
	private UserHome userHome;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setUserHome(UserHome userHome){
		this.userHome = userHome;
	}
	
	
	public void addUser(User user){
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

}
