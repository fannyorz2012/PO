package com.opinion.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opinion.model.User;
import com.opinion.service.UserService;

@Controller
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> handle(@RequestBody User user)
			throws IOException {

		System.out.println("Username From Client : " + user.getName());
		this.userService.addUser(user);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("msg", user.getName() + ",successfully saved");
		return (model);

	}

}
