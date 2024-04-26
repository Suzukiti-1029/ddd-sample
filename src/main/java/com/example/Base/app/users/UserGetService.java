package com.example.base.app.users;

public interface UserGetService {
	UserData handle(UserGetCommand command) throws Exception;
}
