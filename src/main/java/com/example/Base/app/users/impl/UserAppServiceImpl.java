package com.example.base.app.users.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.example.base.app.users.UserData;
import com.example.base.app.users.UserUpdateCommand;
import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;
import com.example.base.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAppServiceImpl {
	private final UserRepository userRepository;
	private final UserService userService;

	public UserData get(String id) throws Exception {
		UserId targetId = new UserId(id);
		User user = userRepository.find(targetId);
		if (Objects.equals(user, null)) {
			return null;
		}
		return new UserData(user);
	}

	public void update(UserUpdateCommand command) throws Exception {
		UserId targetId = new UserId(command.getId());
		User user = userRepository.find(targetId);
		if (Objects.equals(user, null)) {
			throw new Exception("指定されたユーザが見つかりません。");
		}
		String name = command.getName();
		if (!StringUtils.equals(name, null)) {
			UserName newUserName = new UserName(name);
			user.changeName(newUserName);
			if (userService.Exists(user)) {
				throw new Exception("ユーザはすでに存在しています。");
			}
		}
		userRepository.save(user);
	}
}
