package com.example.Base.Domain.Entity;

import java.util.Objects;
import java.util.UUID;

import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.Domain.ValueObject.UserName;

import lombok.Getter;

@Getter
public class User {
	public User(UserName userName) throws Exception {
		if (Objects.equals(userName, null))
			throw new Exception("User: userName must not be null.");
		id = new UserId(UUID.randomUUID().toString());
		name = userName;
	}

	public User(UserId userId, UserName userName) throws Exception {
		this(userName);
		if (Objects.equals(userId, null))
			throw new Exception("User: userId must not be null.");
		id = userId;

	}

	private UserId id;
	private UserName name;

	public void changeName(UserName name) throws Exception {
		if (Objects.equals(name, null))
			throw new Exception("changeName: userName must not be null.");
		this.name = name;
	}
}
