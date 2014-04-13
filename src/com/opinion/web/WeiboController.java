package com.opinion.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

import com.opinion.model.Weibo;
import com.opinion.service.WeiboService;

@Controller
public class WeiboController {
	private WeiboService weiboService;

	@Autowired
	public WeiboController(WeiboService weiboService) {
		this.weiboService = weiboService;
	}

	// 微博授权
	@RequestMapping(value = "/oauth_weibodata")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> oauthWeiboData(
			@RequestParam("testparam") String testparam) throws IOException {

		Oauth oauth = new Oauth();
		try {
			BareBonesBrowserLaunch.openURL(oauth.authorize("code", ""));
		} catch (WeiboException e1) {
			// TODO Auto-generated catch block
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

	// 发微博
	@RequestMapping(value = "/send_weibodata", method = RequestMethod.POST)
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

	// 抓取微博并存入数据库
	@RequestMapping(value = "/catch_weibodata", method = RequestMethod.POST)
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
				this.weiboService.addNewSatus(s);
			}
			System.out.println(status.getNextCursor());
			System.out.println(status.getPreviousCursor());
			System.out.println(status.getTotalNumber());
			System.out.println(status.getHasvisible());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/get_newStatusList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNewStatusList(
			@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException {

		// searchKeyword = Encoder.encode(searchKeyword);
		Map<String, Object> myNewStatusList = this.weiboService.getNewStatusList(start, limit);
		return (myNewStatusList);

	}

}
