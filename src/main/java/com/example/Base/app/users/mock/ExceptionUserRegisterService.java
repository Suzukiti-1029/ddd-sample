package com.example.Base.app.users.mock;

import com.example.Base.app.users.UserRegisterCommand;
import com.example.Base.app.users.UserRegisterService;

public class ExceptionUserRegisterService implements UserRegisterService {
	@Override
	public void handle(UserRegisterCommand command) throws Exception {
		throw new Exception("例外が発生しました。");
	}
}
