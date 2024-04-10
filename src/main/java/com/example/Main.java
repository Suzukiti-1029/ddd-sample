package com.example;

import com.example.Base.Entity.User;
import com.example.Base.Entity.Service.UserService;
import com.example.Base.ValueObject.UserName;

public class Main {
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.createUser(args[0]);
	}

	private void createUser(String userName) throws Exception {
		User user = new User(new UserName(userName));
		UserService userService = new UserService();
		if (userService.Exists(user)) {
			throw new Exception(userName + "はすでに存在しています。");
		}
		// ユーザ登録処理
	}
}
