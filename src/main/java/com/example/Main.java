package com.example;

import com.example.Base.Entity.User;
import com.example.Base.Entity.Service.UserService;
import com.example.Base.Repository.UserRepository;
import com.example.Base.ValueObject.UserName;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Main {
	private final UserRepository userRepository;
	private final UserService userService;

	public static void main(String[] args) throws Exception {
		// TODO
		UserRepository uRepository = null;
		UserService uService = null;
		Main main = new Main(uRepository, uService);
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
