package com.example.Base.Repository;

import com.example.Base.Entity.User;
import com.example.Base.ValueObject.UserName;

public interface UserRepository {
	void save(User user) throws Exception;

	User find(UserName userName) throws Exception;
}
