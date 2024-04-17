package com.example.Base.app.users.mock;

import com.example.Base.app.users.UserRegisterCommand;
import com.example.Base.app.users.UserRegisterService;

public class MockUserRegisterService implements UserRegisterService {
	@Override
	public void handle(UserRegisterCommand command) {
		// モック用になにかする
		return;
	}
}
