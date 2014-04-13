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
import com.opinion.model.Weibo;
import com.opinion.model.WeiboHome;
import com.opinion.model.XlNewStatus;
import com.opinion.model.XlNewStatusHome;
//changechange
@Service
public class WeiboService {
	private WeiboHome weiboHome;
	private XlNewStatusHome xlNewStatusHome;
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
	public void setNewStatusHome(XlNewStatusHome xlNewStatusHome){
		this.xlNewStatusHome = xlNewStatusHome;
	}
	
	

	public void addXlNewSatus(Status s) {
		XlNewStatus xlNewStatus = new XlNewStatus();
		xlNewStatus.setCreatedAt(s.getCreatedAt());
		xlNewStatus.setId(s.getId());
		xlNewStatus.setMid(s.getMid());
		xlNewStatus.setText(s.getText());
		Session session = sessionFactory.getCurrentSession();
		session.save(xlNewStatus);
	}

	public Map<String, Object> getNewStatusList(String start, String limit) {

		String hql = "from XlNewStatus";
		String totalConut = null;
		List<XlNewStatus> results = null;
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
