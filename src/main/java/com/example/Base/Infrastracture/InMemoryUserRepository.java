package com.example.Base.Infrastracture;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.Domain.ValueObject.UserName;

public class InMemoryUserRepository implements UserRepository {
	// 疑似DB
	public static Map<UserId, User> store = new LinkedHashMap<>();

	@Override
	public void save(User user) throws Exception {
		store.put(user.getId(), clone(user));
	}

	@Override
	public User find(UserName userName) throws Exception {
		Optional<User> target = store.values().stream().filter(user -> userName.equals(user.getName())).findFirst();
		if (target.isPresent())
			return clone(target.get());
		else
			return null;
	}

	private User clone(User user) throws Exception {
		return new User(user.getId(), user.getName());
	}
}
