package com.example.base.app.users;

public interface UserDeleteService {
	void handle(UserDeleteCommand command) throws Exception;
}
