package com.example.base.app.users;

public interface UserUpdateService {
	void handle(UserUpdateCommand command) throws Exception;
}
