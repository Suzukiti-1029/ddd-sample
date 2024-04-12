package com.example;

import com.example.Base.Entity.User;
import com.example.Base.Entity.Service.UserService;
import com.example.Base.Repository.UserRepository;
import com.example.Base.ValueObject.UserName;

public class Main {
	private final UserRepository userRepository;
	private final UserService userService;

	public Main(UserRepository uRepository) {
		userRepository = uRepository;
		userService = new UserService(uRepository);
	}

	public static void main(String[] args) throws Exception {
		// TODO
		UserRepository uRepository = null;
		Main main = new Main(uRepository);
		main.createUser(args[0]);
	}

	public void createUser(String userName) throws Exception {
		User user = new User(new UserName(userName));
		if (userService.Exists(user)) {
			throw new Exception(userName + "はすでに存在しています。");
		}
		userRepository.save(user);
	}
}
