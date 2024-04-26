package com.example.base.app.users.impl;

import java.util.Objects;

import com.example.base.app.users.UserData;
import com.example.base.app.users.UserGetCommand;
import com.example.base.app.users.UserGetService;
import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
	private final UserRepository userRepository;

	public UserData handle(UserGetCommand command) throws Exception {
		UserId targetId = new UserId(command.getId());
		User user = userRepository.find(targetId);
		if (Objects.equals(user, null)) {
			return null;
		}
		return new UserData(user);
	}
}
