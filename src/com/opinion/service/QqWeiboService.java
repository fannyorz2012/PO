package com.opinion.service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.opinion.common.Encoder;
import com.opinion.model.QqWeibo;
import com.opinion.model.QqWeiboHome;
import com.tencent.weibo.api.SearchAPI;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.oauthv2.OAuthV2Client;

//changechange
@Service
public class QqWeiboService {
	private QqWeiboHome qqWeiboHome;
	private SessionFactory sessionFactory;
	private static OAuthV2 oAuth = new OAuthV2();

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setWeiboHome(QqWeiboHome qqWeiboHome) {
		this.qqWeiboHome = qqWeiboHome;
	}

	public Map<String, Object> getQqWeiboList(String start, String limit) {

		String hql = "from QqWeibo";
		String totalConut = null;
		List<QqWeibo> results = null;
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
	
	private static void init(OAuthV2 oAuth) {

		oAuth.setClientId("801433372");
		oAuth.setClientSecret("715dab9e16fbc0500e2feb108ba96623");
		oAuth.setRedirectUri("http://www.baidu.com/");
		oAuth.setAccessToken("4b6df4537225d6cd10c9ecb6f29e834c");
		oAuth.setOpenid("90547807C1DB80A00ED08C2C03CAAFF2");
		oAuth.setOpenkey("5E63D1A8120E777F4B01F116BB1EB6F7");
		oAuth.setExpiresIn("8035200");

	}
	
	public class TestParams {
		/*
		 * -----------------------------------------简化测试参数
		 * begin-----------------------------------------------
		 * 下列每个参数的具体含义和取值，请参看其所在函数的doc文档
		 */
		String response;
		String format = "json";
		String clientip = "127.0.0.1";
		String jing = "";
		String wei = "";
		String syncflag = "";
		String pageflag = "0";
		String pagetime = "0";
		String reqnum = "5";
		String lastid = "'0";
		String contenttype = "0";
		String content = "2";// 注意：因为后台会对微博内容进行判重，所以在重复测试时加上变换部分++++++++
		String twitterid = "0";
		String fopenids = "";
		String fopenid = "";
		String reid = null;
		String ids = null;
		String id = null;
		String names = "api_weibo,t-qq-com,vvtest1";
		String name = "t-qq-com";
		String flag = "2";
		String keyword = "微博";
		String pagesize = "5";
		String page = "0";
		String searchtype = "0";
		String msgtype = "0";
		String sorttype = "0";
		String type = "0";
		String op = "0";
		String starttime = "";
		String endtime = "";
		String province = "";
		String city = "";
		String longitue = "";
		String latitude = "";
		String radius = "";
		String startindex = "0";
		String mode = "0";
		String install = "0";
		String picpath = System.getProperty("user.dir")
				+ "\\src\\main\\resources\\logo_QWeibo.jpg";
		/*
		 * -----------------------------------------简化测试参数
		 * end-----------------------------------------------
		 */
	}
	
	public void openBrowser(OAuthV2 oAuth) {

		String authorizationUrl = OAuthV2Client.generateAuthorizationURL(oAuth);

		// 调用外部浏览器
		if (!java.awt.Desktop.isDesktopSupported()) {

			System.err.println("Desktop is not supported (fatal)");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (desktop == null
				|| !desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {

			System.err
					.println("Desktop doesn't support the browse action (fatal)");
			System.exit(1);
		}
		try {
			desktop.browse(new URI(authorizationUrl));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out
				.println("Input the authorization information (eg: code=CODE&openid=OPENID&openkey=OPENKEY) :");
		Scanner in = new Scanner(System.in);
		String responseData = in.nextLine();
		in.close();

		if (OAuthV2Client.parseAuthorization(responseData, oAuth)) {
			System.out.println("Parse Authorization Information Successfully");
		} else {
			System.out.println("Fail to Parse Authorization Information");
			return;
		}
	}

	
	public void sendQqWeibodata() throws Exception {
		System.out.println("sendQqWeibodata  Response from server：");
		
		TestParams testParams = new TestParams();
		
		init(oAuth);
		 System.out.println("oAuth.getAccessToken() = "+oAuth.getAccessToken()); 
		
		/*
		 * ---------------------------------------- 微博相关测试例
		 * begin--------------------------------------------- 注意：
		 * 微博服务器对发微博的频率有限制，如果不加 sleep() 直接执行下列多条发微博操作， 可能会出现 ret=4 errcode=10
		 * 的错误码，意思是：发表太快，被频率限制
		 */ 
		  TAPI tAPI=new TAPI(oAuth.getOauthVersion());//根据oAuth配置对应的连接管理器
		  
		  //取得返回结果 
		  testParams.response=tAPI.add(oAuth, testParams.format, "故都的秋 秋天 测试发表文字微博"+testParams.content, testParams.clientip, testParams.jing, testParams.wei, testParams.syncflag); 
		  // json数据使用 response的结果可能是这样，{"data":{"id":"90221131024999","time":1333002978},"errcode"		  :0,"msg":"ok","ret":0}
		  // 下面的代码将取出 id 的对应值，并赋予 reid
		 
		  System.out.println("response = "+testParams.response); 
		  JSONObject responseJsonObject; 
		  JSONObject dataJsonObject;
		  responseJsonObject= JSONObject.fromObject(testParams.response);
		  dataJsonObject=(JSONObject)responseJsonObject.get("data");
		  testParams.id=testParams.ids=testParams.reid=dataJsonObject.get("id").toString();//对后面用到的 reid 赋值
		  System.out.println("reid = "+ testParams.reid);
		  try { 
			  Thread.sleep ( 5000 ) ; 
			  }catch (InterruptedException ie){}
		  
		  tAPI.reList(oAuth, testParams.format, testParams.flag, testParams.id, testParams.pageflag, testParams.pagetime, testParams.reqnum,
				  testParams.twitterid); 
		  //tAPI.del(oAuth, testParams.format, testParams.id);
		  
		  tAPI.shutdownConnection();//关闭连接管理器
		  
		  
		 /* ------------------------------------------ 微博相关测试例
		 * end--------------------------------------------
		 */

	}

	public void catchQqweibo() throws Exception {
		System.out.println("catchQqweibo  Response from server：");
		TestParams testParams = new TestParams();
		
		init(oAuth);
		 System.out.println("oAuth.getAccessToken() = "+oAuth.getAccessToken()); 
		/*
		 * -------------------------------------------- 搜索相关测试例
		 * begin-----------------------------------------------
		 */
		System.out.println("**************begin searchAPI************");
		SearchAPI searchAPI = new SearchAPI(oAuth.getOauthVersion());

		String searchResponse = searchAPI.t(oAuth, testParams.format, testParams.keyword, testParams.pagesize,
				testParams.page, testParams.contenttype, testParams.sorttype, testParams.msgtype, testParams.searchtype, testParams.starttime,
				testParams.endtime, testParams.province, testParams.city, testParams.longitue, testParams.latitude, testParams.radius);

		System.out.println("**************searchAPI response = "
				+ searchResponse);
		searchAPI.shutdownConnection();
		/*
		 * --------------------------------------------- 搜索相关测试例 end
		 * ------------------------------------------------
		 */

	}
	
	
}
