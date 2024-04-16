package com.example.Base.App;

import java.util.Objects;

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
}
