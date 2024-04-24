package com.example.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.example.base.app.users.UserRegisterService;
import com.example.base.app.users.impl.UserRegisterServiceImpl;
import com.example.base.domain.repository.UserRepository;
import com.example.base.domain.service.UserService;
import com.example.base.infrastructure.HibernateUserRepository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
public class DependencyConfig {
	@Bean
	@Profile("production")
	@Scope("singleton")
	EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("myPU");
	}

	@Bean("userRepository")
	@Profile("production")
	@Scope("singleton")
	UserRepository userRepositoryForProd(EntityManagerFactory entityManagerFactory) {
		return new HibernateUserRepository(entityManagerFactory);
	}

	@Bean
	@Scope("prototype")
	UserService userService(UserRepository userRepository) {
		return new UserService(userRepository);
	}

	@Bean("userRegisterService")
	@Scope("prototype")
	@Profile("production")
	UserRegisterService userRegisterServiceProd(
			UserRepository userRepository,
			UserService userService) {
		return new UserRegisterServiceImpl(userRepository, userService);
	}

}
