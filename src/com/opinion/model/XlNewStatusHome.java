package com.opinion.model;

// Generated 2014-4-13 14:57:01 by Hibernate Tools 4.0.0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class XlNewStatus.
 * @see com.opinion.model.XlNewStatus
 * @author Hibernate Tools
 */

@Repository
public class XlNewStatusHome {

	private static final Log log = LogFactory.getLog(XlNewStatusHome.class);

	private SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(XlNewStatus transientInstance) {
		log.debug("persisting XlNewStatus instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void update(XlNewStatus instance) {
		log.debug("attaching dirty XlNewStatus instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(XlNewStatus persistentInstance) {
		log.debug("deleting XlNewStatus instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public XlNewStatus merge(XlNewStatus detachedInstance) {
		log.debug("merging XlNewStatus instance");
		try {
			XlNewStatus result = (XlNewStatus) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public XlNewStatus findById(java.lang.Integer id) {
		log.debug("getting XlNewStatus instance with id: " + id);
		try {
			XlNewStatus instance = (XlNewStatus) sessionFactory
					.getCurrentSession().get("com.opinion.model.XlNewStatus",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(XlNewStatus instance) {
		log.debug("finding XlNewStatus instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.opinion.model.XlNewStatus")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}