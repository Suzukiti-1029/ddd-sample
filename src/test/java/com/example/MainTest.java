package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Infrastructure.InMemoryUserRepository;

public class MainTest {
	@Test
	public void successCreateUserTest() throws Exception {
		UserRepository userRepository = new InMemoryUserRepository();
		Main main = new Main(userRepository);
		main.createUser("suzuki");

		String head = InMemoryUserRepository.store.values().stream()
				.findFirst().map(user -> user.getName().getValue()).orElse("");
		assertEquals("suzuki", head);
	}
}
