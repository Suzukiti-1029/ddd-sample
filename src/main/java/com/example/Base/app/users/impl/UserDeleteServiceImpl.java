package com.example.Base.app.users.impl;

import java.util.Objects;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.app.users.UserDeleteCommand;

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
