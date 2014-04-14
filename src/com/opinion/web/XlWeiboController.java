package com.opinion.web;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import com.opinion.service.XlWeiboService;
import com.tencent.weibo.api.FriendsAPI;
import com.tencent.weibo.api.InfoAPI;
import com.tencent.weibo.api.PrivateAPI;
import com.tencent.weibo.api.SearchAPI;
import com.tencent.weibo.api.StatusesAPI;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import com.tencent.weibo.utils.QHttpClient;

@Controller
public class XlWeiboController {
	private XlWeiboService xlWeiboService;
	private static OAuthV2 oAuth = new OAuthV2();

	@Autowired
	public XlWeiboController(XlWeiboService xlWeiboService) {
		this.xlWeiboService = xlWeiboService;
	}

	// 新浪微博授权
	@RequestMapping(value = "/oauth_xlweibodata")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> oauthWeiboData(
			@RequestParam("testparam") String testparam) throws IOException {

		Oauth oauth = new Oauth();
		try {
			BareBonesBrowserLaunch.openURL(oauth.authorize("code", ""));
		} catch (WeiboException e1) {
			e1.printStackTrace();
		}
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try {
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				Log.logInfo("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "successfully saved");
		return (model);
	}

	// 发新浪微博
	@RequestMapping(value = "/send_xlweibodata", method = RequestMethod.POST)
	@ResponseBody
	public void sendWeibodata(@RequestParam("testparam") String testparam)
			throws IOException {

		String access_token = "2.00O1u8JC68W4FBc3ff6bba05yP7NgE";
		String statuses = "不要和陌生人说话。20140411";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			Status status = tm.UpdateStatus(statuses);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

	// 抓取新浪微博并存入数据库
	@RequestMapping(value = "/catch_xlweibodata", method = RequestMethod.POST)
	@ResponseBody
	public void catchWeibodata(@RequestParam("testparam") String testparam)
			throws IOException {

		String access_token = "2.00O1u8JC68W4FBc3ff6bba05yP7NgE";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			StatusWapper status = tm.getPublicTimeline();
			for (Status s : status.getStatuses()) {
				Log.logInfo(s.toString());
				System.out.println(s.getId());
				System.out.println(s.getText());
				this.xlWeiboService.addXlWeibo(s);
			}
			System.out.println(status.getNextCursor());
			System.out.println(status.getPreviousCursor());
			System.out.println(status.getTotalNumber());
			System.out.println(status.getHasvisible());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	
	//store:XlNewStatusStore
	@RequestMapping(value = "/get_xlWeiboList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getXlWeiboList(
			@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException {

		// searchKeyword = Encoder.encode(searchKeyword);
		Map<String, Object> myNewStatusList = this.xlWeiboService.getXlWeiboList(start, limit);
		return (myNewStatusList);

	}

	
	
	//

		
}
