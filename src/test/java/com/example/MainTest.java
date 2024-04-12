package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.Base.Repository.UserRepository;
import com.example.Base.Repository.Impl.InMemoryUserRepository;

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
