package com.example.base.domain.service;

import java.util.Objects;

import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public boolean Exists(User user) throws Exception {
		return !Objects.equals(userRepository.find(user.getName()), null);
	}
}
