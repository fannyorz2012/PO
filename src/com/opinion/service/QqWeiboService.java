package com.opinion.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ibm.nlp.chinese.tokenizaer.core.Token;
import org.ibm.nlp.chinese.tokenizaer.core.Tokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

//import ICTCLAS.I3S.AC.ICTCLAS50;

import ICTCLAS.I3S.AC.ICTCLAS50;
import ICTCLAS.I3S.AC.QqFenci;

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
		 * -----------------------------------------绠�寲娴嬭瘯鍙傛暟
		 * begin-----------------------------------------------
		 * 涓嬪垪姣忎釜鍙傛暟鐨勫叿浣撳惈涔夊拰鍙栧�锛岃鍙傜湅鍏舵墍鍦ㄥ嚱鏁扮殑doc鏂囨。
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
		String content = "2";// 娉ㄦ剰锛氬洜涓哄悗鍙颁細瀵瑰井鍗氬唴瀹硅繘琛屽垽閲嶏紝鎵�互鍦ㄩ噸澶嶆祴璇曟椂鍔犱笂鍙樻崲閮ㄥ垎++++++++
		String twitterid = "0";
		String fopenids = "";
		String fopenid = "";
		String reid = null;
		String ids = null;
		String id = null;
		String names = "api_weibo,t-qq-com,vvtest1";
		String name = "t-qq-com";
		String flag = "2";
		String keyword = "寰崥";
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
		 * -----------------------------------------绠�寲娴嬭瘯鍙傛暟
		 * end-----------------------------------------------
		 */
	}

	public void openBrowser(OAuthV2 oAuth) {

		String authorizationUrl = OAuthV2Client.generateAuthorizationURL(oAuth);

		// 璋冪敤澶栭儴娴忚鍣�
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
		System.out.println("sendQqWeibodata  Response from ser");

		TestParams testParams = new TestParams();

		init(oAuth);
		System.out
				.println("oAuth.getAccessToken() = " + oAuth.getAccessToken());

		/*
		 * ---------------------------------------- 寰崥鐩稿叧娴嬭瘯渚�
		 * begin--------------------------------------------- 娉ㄦ剰锛�
		 * 寰崥鏈嶅姟鍣ㄥ鍙戝井鍗氱殑棰戠巼鏈夐檺鍒讹紝濡傛灉涓嶅姞 sleep() 鐩存帴鎵ц涓嬪垪澶氭潯鍙戝井鍗氭搷浣滐紝
		 * 鍙兘浼氬嚭鐜�ret=4 errcode=10 鐨勯敊璇爜锛屾剰鎬濇槸锛氬彂琛ㄥお蹇紝琚鐜囬檺鍒�
		 */
		TAPI tAPI = new TAPI(oAuth.getOauthVersion());// 鏍规嵁oAuth閰嶇疆瀵瑰簲鐨勮繛鎺ョ鐞嗗櫒

		// 鍙栧緱杩斿洖缁撴灉
		testParams.response = tAPI.add(oAuth, testParams.format,
				"鏁呴兘鐨勭 绉嬪ぉ 娴嬭瘯鍙戣〃鏂囧瓧寰崥" + testParams.content,
				testParams.clientip, testParams.jing, testParams.wei,
				testParams.syncflag);
		// json鏁版嵁浣跨敤
		// response鐨勭粨鏋滃彲鑳芥槸杩欐牱锛寋"data":{"id":"90221131024999","time":1333002978},"errcode"
		// :0,"msg":"ok","ret":0}
		// 涓嬮潰鐨勪唬鐮佸皢鍙栧嚭 id 鐨勫搴斿�锛屽苟璧嬩簣 reid

		System.out.println("response = " + testParams.response);
		JSONObject responseJsonObject;
		JSONObject dataJsonObject;
		responseJsonObject = JSONObject.fromObject(testParams.response);
		dataJsonObject = (JSONObject) responseJsonObject.get("data");
		testParams.id = testParams.ids = testParams.reid = dataJsonObject.get(
				"id").toString();// 瀵瑰悗闈㈢敤鍒扮殑 reid 璧嬪�
		System.out.println("reid = " + testParams.reid);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ie) {
		}

		tAPI.reList(oAuth, testParams.format, testParams.flag, testParams.id,
				testParams.pageflag, testParams.pagetime, testParams.reqnum,
				testParams.twitterid);
		// tAPI.del(oAuth, testParams.format, testParams.id);

		tAPI.shutdownConnection();// 鍏抽棴杩炴帴绠＄悊鍣�

		/*
		 * ------------------------------------------ 寰崥鐩稿叧娴嬭瘯渚�
		 * end--------------------------------------------
		 */

	}

	public void catchQqweibo() throws Exception {
		System.out.println("catchQqweibo  Response from serve");
		TestParams testParams = new TestParams();

		init(oAuth);
		System.out
				.println("oAuth.getAccessToken() = " + oAuth.getAccessToken());
		/*
		 * -------------------------------------------- 鎼滅储鐩稿叧娴嬭瘯渚�
		 * begin-----------------------------------------------
		 */
		System.out.println("**************begin searchAPI************");
		SearchAPI searchAPI = new SearchAPI(oAuth.getOauthVersion());

		String searchResponse = searchAPI.t(oAuth, testParams.format,
				testParams.keyword, testParams.pagesize, testParams.page,
				testParams.contenttype, testParams.sorttype,
				testParams.msgtype, testParams.searchtype,
				testParams.starttime, testParams.endtime, testParams.province,
				testParams.city, testParams.longitue, testParams.latitude,
				testParams.radius);

		System.out.println("**************searchAPI response = "
				+ searchResponse);
		searchAPI.shutdownConnection();
		/*
		 * --------------------------------------------- 鎼滅储鐩稿叧娴嬭瘯渚�end
		 * ------------------------------------------------
		 */

	}

	public void saveQqWeibo(String string) {

		QqWeibo qqweibo = new QqWeibo();
		qqweibo.setText(string);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(qqweibo);
		} catch (Exception er) {
			System.out.println(er.getMessage());
		}

	}

	public String fenci() throws UnsupportedEncodingException {

		System.out.print("fenci: ");

		String result = "";
		
		List<QqWeibo> qqWeiboList = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession()
					.createQuery("from QqWeibo");

			qqWeiboList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sInput = null;

		for (int i = 0; i < qqWeiboList.size(); i++) {
			QqWeibo a = qqWeiboList.get(i);
			sInput = a.getText();
			System.out.println(sInput);		
			QqFenci.main(null);

		}
		
		
		
		

		return result;

	}

	public void updateEasilyFenci() throws IOException {

		Session session = sessionFactory.getCurrentSession();
		List<QqWeibo> qqWeiboList = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession()
					.createQuery("from QqWeibo");

			qqWeiboList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sInput = null;

		for (int i = 0; i < qqWeiboList.size(); i++) {
			
			String fenci_result = "";
			QqWeibo a = qqWeiboList.get(i);
			sInput = a.getText();
			System.out.println(sInput);	
			
			
			Tokenizer tokenizer=new Tokenizer(true);
		    
		    long start=System.currentTimeMillis();
			long memUsed1=used();
			
			tokenizer.setInputStream(sInput);
			Token token=tokenizer.getNextToken();
			while(token !=null){
				
				System.out.print(token.getString()+"|");
				fenci_result = fenci_result+ token.getString()+"|";
				token=tokenizer.getNextToken();
			}

			System.out.println("fenci_result: "+fenci_result);
			System.out.println("fenci done");
			a.setFenci(fenci_result);
			a.setId("123456");
			session.saveOrUpdate(a);			
			session.flush();
			long memUsed2=used();
		    long end=System.currentTimeMillis();
		    System.out.println("time used: "+(end-start)+" ms");
		    System.out.println("mem used: "+memUsed2+" - "+memUsed1+" = "+(memUsed2-memUsed1)+" Byte");
		}
		
		
		
		
		
	}
	public static long used() {
		   long total=Runtime.getRuntime().totalMemory();
		   long free=Runtime.getRuntime().freeMemory();
		   return (total-free);
	}
	

}
