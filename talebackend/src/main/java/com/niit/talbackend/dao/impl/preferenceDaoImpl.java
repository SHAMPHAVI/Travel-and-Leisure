package com.niit.talbackend.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.talbackend.dao.preferenceDao;
import com.niit.talbackend.model.preference;
@Repository("shipaddrsdao")
@Transactional
public class preferenceDaoImpl implements preferenceDao{
	public static Logger log=LoggerFactory.getLogger(preferenceDaoImpl.class);
	@Autowired
	SessionFactory factory;
	public preferenceDaoImpl(SessionFactory factory){
		this.factory=factory;
	}

	public boolean saveshipaddrs(preference address) {
		log.debug("save shippaddress starts");
		try {
			if(get(address.getId())!=null){
				return false;
			}
			factory.getCurrentSession().save(address);
			log.debug("save shippaddress ends");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean Updateshipaddrs(preference address) {
		log.debug("update shippaddress starts");

		try {
			if(get(address.getId())==null){
				return false;
			}	factory.getCurrentSession().update(address);
			log.debug("update shippaddress ends");

			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteshipaddrs(preference address) {
		log.debug("delete shippaddress starts");

		try {
			if(get(address.getId())==null){
				return false;
			}	
			factory.getCurrentSession().delete(address);
			log.debug("delete shippaddress ends");

			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public preference get(String id) {
		return (preference) factory.openSession().get(preference.class,id);

	}

	public List<preference> listofAddresses() {
		String hql="from preference";
		Query query=factory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public String getpreferenceid() {
		if(get("ship01")==null){
			return "ship00";
		}
		String hql="select max(id) from preference";
		Query query=factory.getCurrentSession().createQuery(hql);
	String a=query.uniqueResult().toString();
	return a;
	}
	}


