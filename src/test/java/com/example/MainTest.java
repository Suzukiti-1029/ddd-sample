package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.base.domain.ValueObject.UserName;
import com.example.base.domain.entity.User;
import com.example.base.domain.repository.UserRepository;
import com.example.base.infrastructure.HibernateUserRepository;
import com.example.base.infrastructure.InMemoryUserRepository;
import com.example.base.infrastructure.PersistenceFactoryManager;

public class MainTest {
	@Test
	public void successCreateUserLogicTest() throws Exception {
		UserRepository userRepository = new InMemoryUserRepository();
		Main main = new Main(userRepository);
		main.createUser("suzuki");

		String head = InMemoryUserRepository.store.values().stream()
				.findFirst().map(user -> user.getName().getValue()).orElse("");
		assertEquals("suzuki", head);
	}

	@Test
	public void successCreateUserDBTest() throws Exception {
		PersistenceFactoryManager pfm = PersistenceFactoryManager.getInstance("myPUtest");
		try {
			UserRepository userRepository = new HibernateUserRepository(pfm);
			Main main = new Main(userRepository);
			main.createUser("suzuki");

			User headUser = userRepository.find(new UserName("suzuki"));
			String head = null;
			if (headUser != null)
				head = headUser.getName().getValue();
			assertEquals("suzuki", head);
		} catch (Exception e) {
			throw e;
		} finally {
			pfm.close();
		}
	}
}
