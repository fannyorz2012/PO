package com.opinion.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
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
		Map<String, Object> myQqWeiboList = this.qqWeiboService.getQqWeiboList(
				start, limit);
		return (myQqWeiboList);

	}

	// 鍙慟Q寰崥
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

	// 鎶撳彇QQ寰崥骞跺瓨鍌�
	@RequestMapping(value = "/catch_qqweibodata")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catchQqweibodata(
			@RequestParam("testparam") String testparam) throws IOException {
		System.out.println("/parser_json");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "successfully saved");

		// 鎵ц娴嬭瘯鍒楄〃
		try {

			this.qqWeiboService.catchQqweibo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (model);
	}

	// 瑙ｆ瀽json
	@RequestMapping(value = "/parser_json")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> parserJson() throws IOException {
		System.out.println("/parser_json");
		StringBuilder stringBuilder = new StringBuilder();
		String encoding = "gbk";
		String s = "";
		// FileReader fr = new FileReader("C:\\tudiliuzhuan100json.txt");
		// 鍙互鎹㈡垚宸ョ▼鐩綍涓嬬殑鍏朵粬鏂囨湰鏂囦欢
		File file = new File("C:\\tudiliuzhuan100json.txt");
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);// 鑰冭檻鍒扮紪鐮佹牸寮�
		BufferedReader br = new BufferedReader(read);
		String line;
		while ((line = br.readLine()) != null) {
			stringBuilder.append(line);
		}

		s = stringBuilder.toString();
		System.out.println(s);
		br.close();
		read.close();

		try {
			JSONObject jo = new JSONObject(s);
			JSONObject datajo = (JSONObject) jo.get("data");
			JSONArray jsonArray = (JSONArray) datajo.get("info");
			for (int i = 0; i < jsonArray.length(); ++i) {
				JSONObject o = (JSONObject) jsonArray.get(i);
				System.out.println("origtext:" + o.getString("origtext"));
				this.qqWeiboService.saveQqWeibo(o.getString("origtext"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "successfully saved");
		return (model);
	}

	// fenci
	@RequestMapping(value = "/fenci")
	// ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fenci() throws IOException {
		System.out.println("fenci controllar begin ");
		//String result = this.qqWeiboService.fenci();
		
		//Test.fc();
		
		this.qqWeiboService.updateEasilyFenci();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", "successfully saved");
		return (model);
	}

}
