package com.example.base.app.users.impl;

import java.util.Objects;

import com.example.base.app.users.UserDeleteCommand;
import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDeleteServiceImpl {
	private final UserRepository userRepository;

	public void handle(UserDeleteCommand command) throws Exception {
		UserId targetId = new UserId(command.getId());
		User user = userRepository.find(targetId);
		if (Objects.equals(user, null)) {
			throw new Exception("指定されたユーザが見つかりません。");
		}
		userRepository.delete(user);
	}
}
