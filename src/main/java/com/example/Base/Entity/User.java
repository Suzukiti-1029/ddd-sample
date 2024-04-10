package com.example.Base.Entity;

import java.util.Objects;
import java.util.UUID;

import com.example.Base.ValueObject.UserId;
import com.example.Base.ValueObject.UserName;

import lombok.Getter;

@Getter
public class User {
	public User(UserName userName) throws Exception {
		if (Objects.equals(userName, null))
			throw new Exception("User: userName must not be null.");
		id = new UserId(UUID.randomUUID().toString());
		name = userName;
	}

	private UserId id;
	private UserName name;
}
