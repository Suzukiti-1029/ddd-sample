package com.example.base.app.users;

public interface UserRegisterService {
	void handle(UserRegisterCommand command) throws Exception;
}
