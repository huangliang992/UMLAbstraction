package com.hainu.cs.action;

import java.util.Map;

import com.hainu.cs.bean.User;
import com.hainu.cs.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private User user=new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String checkUser(){
		UserDao ud=new UserDao();
		User u=ud.queryUser(user.getUsername(), user.getPassword());
		if(u==null){
			return "ERROR";
		}else{
			ActionContext actioncontext=ActionContext.getContext();
			Map session=actioncontext.getSession();
			session.put("user", u);
			return "SUCCESS";
		}
	}
	
	public String execute(){
		UserDao ud=new UserDao();
		ud.insert(user);
		//System.out.println(user.getUsername());
		return "SUCCESS";
	}
}
