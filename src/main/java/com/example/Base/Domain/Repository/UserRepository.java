package com.example.base.domain.repository;

import com.example.base.domain.ValueObject.UserId;
import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;

public interface UserRepository {
	User find(UserId userId); // throws Exception;

	User find(UserName userName) throws Exception;

	void save(User user) throws Exception;

	void delete(User user); // throws Exception;
}
