package com.example.Base.Domain.Service;

import java.util.Objects;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public boolean Exists(User user) throws Exception {
		return !Objects.equals(userRepository.find(user.getName()), null);
	}
}
