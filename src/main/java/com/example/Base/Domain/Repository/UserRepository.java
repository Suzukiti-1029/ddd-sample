package com.example.Base.Domain.Repository;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.ValueObject.UserId;
import com.example.Base.Domain.ValueObject.UserName;

public interface UserRepository {
	User find(UserId userId); // throws Exception;

	User find(UserName userName) throws Exception;

	void save(User user) throws Exception;

	void delete(User user); // throws Exception;
}
