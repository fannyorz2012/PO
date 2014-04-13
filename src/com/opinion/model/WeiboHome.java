package com.opinion.model;

// Generated 2014-3-31 18:49:58 by Hibernate Tools 4.0.0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Weibo.
 * @see com.opinion.model.Weibo
 * @author Hibernate Tools
 */

@Repository
public class WeiboHome {

	private static final Log log = LogFactory.getLog(WeiboHome.class);

	private SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(Weibo transientInstance) {
		log.debug("persisting Weibo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void update(Weibo instance) {
		log.debug("attaching dirty Weibo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Weibo persistentInstance) {
		log.debug("deleting Weibo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Weibo merge(Weibo detachedInstance) {
		log.debug("merging Weibo instance");
		try {
			Weibo result = (Weibo) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Weibo findById(java.lang.Integer id) {
		log.debug("getting Weibo instance with id: " + id);
		try {
			Weibo instance = (Weibo) sessionFactory.getCurrentSession().get(
					"com.opinion.model.Weibo", id);
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

	public List findByExample(Weibo instance) {
		log.debug("finding Weibo instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.opinion.model.Weibo")
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
