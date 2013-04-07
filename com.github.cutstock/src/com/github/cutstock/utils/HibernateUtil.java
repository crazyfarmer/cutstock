package com.github.cutstock.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.github.cutstock.db.HibernateConfiguration;
import com.github.cutstock.db.InstancePool;
import com.github.cutstock.db.SessionConfigure;
import com.github.cutstock.db.beans.CodeNameTransRule;

public class HibernateUtil {

	private static String className;
	private Session session;
	private Query query;
	
	public HibernateUtil(String className) {
		this.className = className;
		SessionConfigure config = getSessionConfigurationInstance(className);
		session = config.openSession();
	}

	public Session openSession() {
		return session;
	}

	public Query createQuery(String hql){
		query = session.createQuery(hql);
		return query;
	}
	private static SessionConfigure getSessionConfigurationInstance(
			String className) {
		className = getSessionConfigurationClassName(className);

		return (SessionConfigure) InstancePool
				.get(getSessionConfigurationClassName(className));
	}

	private static String getSessionConfigurationClassName(String className) {
		if (className == null) {
			className = HibernateConfiguration.class.getName();
		}
		return className;
	}

	public void delete(Object obj) throws HibernateException {
		try {
			Session session = openSession();
			Transaction ts = session.beginTransaction();
			session.delete(obj);
			session.flush();
			ts.commit();
		} catch (Exception e) {
			throw new HibernateException("Error deleting object ", e);
		}
	}

	public void delete(String sql) throws HibernateException {
		try {
			Session session = openSession();
			Transaction ts = session.beginTransaction();
			session.delete(sql);
			ts.commit();
		} catch (Exception e) {
			throw new HibernateException("Error deleteing SQL ", e);
		}
	}

	public void save(Object obj) throws HibernateException {
		try {
			Session session = openSession();
			Transaction ts = session.beginTransaction();
			session.save(obj);
			session.flush();
			ts.commit();
		} catch (Exception e) {
			throw new HibernateException(
					"Unable to save Object to Hibernate Session ", e);
		}
	}

	public void saveOrUpdate(Object obj) throws HibernateException {
		try {
			Session session = openSession();
			Transaction ts = session.beginTransaction();
			session.saveOrUpdate(obj);
			session.flush();
			ts.commit();
		} catch (Exception e) {
			throw new HibernateException(
					"Unable to save/update Object to Hibernate Session ", e);
		}
	}

	public void update(Object obj) throws HibernateException {
		try {
			Session session = openSession();
			Transaction ts = session.beginTransaction();
			session.update(obj);
			ts.commit();
		} catch (Exception e) {
			throw new HibernateException(
					"Unable to update Object to Hibernate Session ", e);
		}
	}

	public int count(String tableName) {
		return selectAllFromTable(tableName).size();
	}

	public List selectAllFromTable(String tableName) {
		session = openSession();
		Transaction ts = session.beginTransaction();
		List result = session.createQuery("FROM " + tableName).list();
		ts.commit();
		return result;
	}

	public void deleteById(String tableName, int id) {
		String hql = "DELETE " + tableName + " WHERE id=?";
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setInteger(0, new Integer(id));
		query.executeUpdate();
		ts.commit();
	}

	public void insert(Object object) {
		Transaction ts = session.beginTransaction();
		session.save(object);
		ts.commit();
	}

	public Object selectById(String tableName, int id) {
		String hql = "FROM " + tableName + " WHERE id= :id";
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List result = query.list();
		ts.commit();
		return result.get(0);
	}

	public List query() {
		session = openSession();
		Transaction ts = session.beginTransaction();
		List result = query.list();
		ts.commit();
		return result;
	}

	public void setParam(String param, Object value) {
		query.setParameter(param, value);
	}
}