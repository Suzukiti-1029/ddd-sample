package com.example.base.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {
	// 疑似DB
	public static Map<UserId, User> store = new LinkedHashMap<>();

	@Override
	public void save(User user) throws Exception {
		store.put(user.getId(), clone(user));
	}

	@Override
	public User find(UserName userName) throws Exception {
		Optional<User> target = store.values().stream()
				.filter(user -> (StringUtils.equals(userName.getValue(), user.getName().getValue()))).findFirst();
		if (target.isPresent())
			return clone(target.get());
		else
			return null;
	}

	private User clone(User user) throws Exception {
		return new User(user.getId(), user.getName());
	}
}
