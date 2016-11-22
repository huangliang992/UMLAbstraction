package com.hainu.cs.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hainu.cs.bean.FileBean;

public class FileInfoDao {
	//往数据库中插入文件信息
	public void insertFileInfo(FileBean fb){
		Session sess=SessionUtil.getSession();
		sess.beginTransaction();
		sess.save(fb);
		sess.getTransaction().commit();
	}
	//从数据库中读取文件信息
	public List checkList(String username, String password){
		Session sess=SessionUtil.getSession();
		sess.beginTransaction();
		String hql="from FileBean where name='"+username+"' and password='"+password+"'";
		Query query=sess.createQuery(hql);
		List obj=query.list();
		return obj;
	}
}
