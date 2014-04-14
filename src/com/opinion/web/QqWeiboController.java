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

import com.opinion.service.QqWeiboService;
import com.tencent.weibo.oauthv2.OAuthV2;


@Controller
public class QqWeiboController {
	private QqWeiboService qqWeiboService;
	

	@Autowired
	public QqWeiboController(QqWeiboService qqWeiboService) {
		this.qqWeiboService = qqWeiboService;
	}

	// store:qqWeiboStore
	@RequestMapping(value = "/get_qqWeiboList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getQqWeiboList(
			@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException {

		// searchKeyword = Encoder.encode(searchKeyword);
		Map<String, Object> myQqWeiboList = this.qqWeiboService
				.getQqWeiboList(start, limit);
		return (myQqWeiboList);

	}

	// 发QQ微博
		@RequestMapping(value = "/send_qqweibodata", method = RequestMethod.POST)
		@ResponseBody
		public void sendQqWeibodata(@RequestParam("testparam") String testparam)
				throws IOException {

		
			try {
				this.qqWeiboService.sendQqWeibodata();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
	
	
	//抓取QQ微博并存储	
	@RequestMapping(value = "/catch_qqweibodata")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catchQqweibodata(
			@RequestParam("testparam") String testparam) throws IOException {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "successfully saved");

		// 执行测试列表
		try {
			
			this.qqWeiboService.catchQqweibo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (model);
	}

	

	

	

}
