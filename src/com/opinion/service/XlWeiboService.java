package com.opinion.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import weibo4j.model.Status;

import com.opinion.common.Encoder;
import com.opinion.model.XlWeibo;
import com.opinion.model.XlWeiboHome;
import com.opinion.model.XlWeiboHome;
//changechange
@Service
public class XlWeiboService {
	private XlWeiboHome xlWeiboHome;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Autowired
	public void setWeiboHome(XlWeiboHome xlWeiboHome){
		this.xlWeiboHome = xlWeiboHome;
	}
	
	

	public void addXlWeibo(Status s) {
		XlWeibo xlWeibo = new XlWeibo();
		xlWeibo.setCreatedAt(s.getCreatedAt());
		xlWeibo.setId(s.getId());
		xlWeibo.setMid(s.getMid());
		xlWeibo.setText(s.getText());
		Session session = sessionFactory.getCurrentSession();
		session.save(xlWeibo);
	}

	public Map<String, Object> getXlWeiboList(String start, String limit) {

		String hql = "from XlWeibo";
		String totalConut = null;
		List<XlWeibo> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession()
					.createQuery(hql);
			totalConut = String.valueOf(query.list().size());
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("total", totalConut);
		myMapResult.put("root", results);

		return myMapResult;
		
	}

}
