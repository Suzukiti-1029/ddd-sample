package com.example.Base.Entity.Service;

import java.util.Objects;

import com.example.Base.Entity.User;
import com.example.Base.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public boolean Exists(User user) throws Exception {
		return Objects.equals(userRepository.find(user.getName()), null);
	}
}
