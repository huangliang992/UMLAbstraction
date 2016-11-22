package com.hainu.cs.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


import com.hainu.cs.bean.User;

public class UserDao {
	private static final Logger logger = Logger.getLogger(UserDao.class); 
	
	public void insert(User user){
		Session session =SessionUtil.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public User queryUser(String name, String password){
		Session session=SessionUtil.getSession();
		session.beginTransaction();
		String str="from User where username='"+name+"'and password='"+password+"'";
		Query query=session.createQuery(str);
		List list=query.list();
		if(list.size()==0){
			return null;
		}else{
		User obj=(User) list.get(0);
		return obj;
		}
	}
	
}
