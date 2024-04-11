package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import com.example.Base.Entity.Service.UserService;
import com.example.Base.Repository.UserRepository;
import com.example.Base.Repository.Impl.InMemoryUserRepository;

public class MainTest {
	@Test
	public void createUserSuccessTest() throws Exception {
		UserRepository uRepository = new InMemoryUserRepository();
		UserService uService = new UserService(uRepository);
		Main main = new Main(uRepository, uService);
		assertDoesNotThrow(() -> main.createUser("suzuki"));
	}
}
