package com.example.base.app.users.mock;

import com.example.base.app.users.UserRegisterCommand;
import com.example.base.app.users.UserRegisterService;

public class MockUserRegisterService implements UserRegisterService {
	@Override
	public void handle(UserRegisterCommand command) {
		// モック用になにかする
		return;
	}
}
