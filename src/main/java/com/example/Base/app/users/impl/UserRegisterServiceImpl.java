package com.example.Base.app.users.impl;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.Service.UserService;
import com.example.Base.Domain.ValueObject.UserName;
import com.example.Base.app.users.UserRegisterCommand;
import com.example.Base.app.users.UserRegisterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {
	private final UserRepository userRepository;
	private final UserService userService;

	public void handle(UserRegisterCommand command) throws Exception {
		String name = command.getName();
		User user = new User(new UserName(name));
		if (userService.Exists(user)) {
			throw new Exception(name + "はすでに存在しています。");
		}
		userRepository.save(user);
	}
}
