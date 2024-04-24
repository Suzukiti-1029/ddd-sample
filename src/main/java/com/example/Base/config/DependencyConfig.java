package com.example.Base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.Service.UserService;
import com.example.Base.Infrastructure.HibernateUserRepository;
import com.example.Base.app.users.UserRegisterService;
import com.example.Base.app.users.impl.UserRegisterServiceImpl;

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
