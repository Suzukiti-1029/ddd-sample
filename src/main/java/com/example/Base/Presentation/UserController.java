package com.example.base.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base.app.users.UserRegisterCommand;
import com.example.base.app.users.UserRegisterService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final UserRegisterService userRegisterService;

	@PostMapping
	public void post(@RequestBody UserPostRequestModel request) throws Exception {
		UserRegisterCommand command = new UserRegisterCommand(request.getName());
		userRegisterService.handle(command);
	}

}
