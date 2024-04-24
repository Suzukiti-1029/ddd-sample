package com.example.base.app.users.impl;

import com.example.base.app.users.UserRegisterCommand;
import com.example.base.app.users.UserRegisterService;
import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;
import com.example.base.domain.service.UserService;

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
