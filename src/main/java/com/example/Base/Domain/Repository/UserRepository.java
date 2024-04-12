package com.example.Base.Domain.Repository;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.ValueObject.UserName;

public interface UserRepository {
	void save(User user) throws Exception;

	User find(UserName userName) throws Exception;
}
