package com.opinion.model;

// Generated 2014-4-14 8:48:37 by Hibernate Tools 4.0.0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class XlWeibo.
 * @see com.opinion.model.XlWeibo
 * @author Hibernate Tools
 */

@Repository
public class XlWeiboHome {

	private static final Log log = LogFactory.getLog(XlWeiboHome.class);

	private SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(XlWeibo transientInstance) {
		log.debug("persisting XlWeibo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void update(XlWeibo instance) {
		log.debug("attaching dirty XlWeibo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(XlWeibo persistentInstance) {
		log.debug("deleting XlWeibo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public XlWeibo merge(XlWeibo detachedInstance) {
		log.debug("merging XlWeibo instance");
		try {
			XlWeibo result = (XlWeibo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public XlWeibo findById(java.lang.Integer id) {
		log.debug("getting XlWeibo instance with id: " + id);
		try {
			XlWeibo instance = (XlWeibo) sessionFactory.getCurrentSession()
					.get("com.opinion.model.XlWeibo", id);
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

	public List findByExample(XlWeibo instance) {
		log.debug("finding XlWeibo instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.opinion.model.XlWeibo")
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
