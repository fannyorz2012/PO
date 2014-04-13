package com.opinion.model;

// Generated 2014-4-12 15:29:39 by Hibernate Tools 4.0.0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class NewStatus.
 * @see com.opinion.model.NewStatus
 * @author Hibernate Tools
 */

@Repository
public class NewStatusHome {

	private static final Log log = LogFactory.getLog(NewStatusHome.class);

	private SessionFactory sessionFactory = null;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(NewStatus transientInstance) {
		log.debug("persisting NewStatus instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void update(NewStatus instance) {
		log.debug("attaching dirty NewStatus instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(NewStatus persistentInstance) {
		log.debug("deleting NewStatus instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NewStatus merge(NewStatus detachedInstance) {
		log.debug("merging NewStatus instance");
		try {
			NewStatus result = (NewStatus) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public NewStatus findById(java.lang.Integer id) {
		log.debug("getting NewStatus instance with id: " + id);
		try {
			NewStatus instance = (NewStatus) sessionFactory.getCurrentSession()
					.get("com.opinion.model.NewStatus", id);
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

	public List findByExample(NewStatus instance) {
		log.debug("finding NewStatus instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.opinion.model.NewStatus")
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
