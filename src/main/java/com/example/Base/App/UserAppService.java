package com.example.Base.App;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.Service.UserService;
import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.Domain.ValueObject.UserName;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAppService {
	private final UserRepository userRepository;
	private final UserService userService;

	public void register(String name) throws Exception {
		User user = new User(new UserName(name));
		if (userService.Exists(user)) {
			throw new Exception(name + "はすでに存在しています。");
		}
		userRepository.save(user);
	}

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

	public void delete(UserDeleteCommand command) throws Exception {
		UserId targetId = new UserId(command.getId());
		User user = userRepository.find(targetId);
		if (Objects.equals(user, null)) {
			throw new Exception("指定されたユーザが見つかりません。");
		}
		userRepository.delete(user);
	}
}
