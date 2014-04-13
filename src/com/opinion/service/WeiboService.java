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
import com.opinion.model.NewStatus;
import com.opinion.model.NewStatusHome;
import com.opinion.model.Weibo;
import com.opinion.model.WeiboHome;
//changechange
@Service
public class WeiboService {
	private WeiboHome weiboHome;
	private NewStatusHome newStatusHome;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setWeiboHome(WeiboHome weiboHome){
		this.weiboHome = weiboHome;
	}
	
	@Autowired
	public void setNewStatusHome(NewStatusHome newStatusHome){
		this.newStatusHome = newStatusHome;
	}
	
	

	public void addNewSatus(Status s) {
		NewStatus newStatus = new NewStatus();
		newStatus.setCreatedAt(s.getCreatedAt());
		newStatus.setId(s.getId());
		newStatus.setMid(s.getMid());
		//newStatus.setText(Encoder.encode(s.getText()));
		newStatus.setText(s.getText());
		Session session = sessionFactory.getCurrentSession();
		session.save(newStatus);
	}

	public Map<String, Object> getNewStatusList(String start, String limit) {

		String hql = "from NewStatus as newStatus";
		String totalConut = null;
		List<NewStatus> results = null;
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
