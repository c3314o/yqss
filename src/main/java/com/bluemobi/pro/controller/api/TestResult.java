package com.bluemobi.pro.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.utils.Result;

@Controller
@RequestMapping("/test/result/")
public class TestResult {

	@RequestMapping(value = "main", method = RequestMethod.POST)
	@ResponseBody
	public Result main(@RequestParam Map<String, Object> params) {
		
		List<User> userList= buildUser();
		return Result.success(userList);
	}

	private List<User> buildUser() {
		
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setId(123456);
		user.setUsername("username");
		user.setPassword("password");
		
		list.add(user);
		return list;
	}
}

class User {

	private int id;
	private String username;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}