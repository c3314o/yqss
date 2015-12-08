//package mxj;
//
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.bluemobi.utils.Result;
//
//@Controller
//@RequestMapping("/test/result/")
//public class TestResult {
//
//	@RequestMapping(value = "main", method = RequestMethod.POST)
//	@ResponseBody
//	public Result main(@RequestParam Map<String, Object> params) {
//		
//		User user = buildUser();
//		return Result.success(user);
//	}
//
//	private User buildUser() {
//		User user = new User();
//		user.setId(123456);
//		user.setUsername("username");
//		user.setPassword("password");
//		return user;
//	}
//}
//
//class User {
//
//	private int id;
//	private String username;
//	private String password;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//}