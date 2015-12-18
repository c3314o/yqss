package mxj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDemo {

	public static void main(String[] args) {
		User user = new User();
		user.setId(2);
		user.setFlag(1);
		user.setUsername("admin");
		
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(user));
		
		
	}
}

class User {
	
	private Integer id;
	
	private Integer flag;
	
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlag() {
		flag = flag + id;
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
