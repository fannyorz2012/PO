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
 * Home object for domain model class QqWeibo.
 * @see com.opinion.model.QqWeibo
 * @author Hibernate Tools
 */

@Repository
public class QqWeiboHome {

	private static final Log log = LogFactory.getLog(QqWeiboHome.class);

	private SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(QqWeibo transientInstance) {
		log.debug("persisting QqWeibo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void update(QqWeibo instance) {
		log.debug("attaching dirty QqWeibo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(QqWeibo persistentInstance) {
		log.debug("deleting QqWeibo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public QqWeibo merge(QqWeibo detachedInstance) {
		log.debug("merging QqWeibo instance");
		try {
			QqWeibo result = (QqWeibo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public QqWeibo findById(java.lang.Integer id) {
		log.debug("getting QqWeibo instance with id: " + id);
		try {
			QqWeibo instance = (QqWeibo) sessionFactory.getCurrentSession()
					.get("com.opinion.model.QqWeibo", id);
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

	public List findByExample(QqWeibo instance) {
		log.debug("finding QqWeibo instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.opinion.model.QqWeibo")
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
