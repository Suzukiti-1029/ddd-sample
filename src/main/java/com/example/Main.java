package com.example;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.Service.UserService;
import com.example.Base.Domain.ValueObject.UserName;
import com.example.Base.Infrastructure.HibernateUserRepository;
import com.example.Base.Infrastructure.PersistenceFactoryManager;
import com.example.Base.app.users.UserRegisterCommand;
import com.example.Base.app.users.UserRegisterService;
import com.example.Base.app.users.UserUpdateCommand;
import com.example.Base.app.users.impl.UserAppServiceImpl;

public class Main {
	private final UserRepository userRepository;
	private final UserService userService;
	private final UserRegisterService userRegisterService;

	public Main(UserRepository uRepository, UserRegisterService uRegisterService) {
		userRepository = uRepository;
		userService = new UserService(uRepository);
		userRegisterService = uRegisterService;
	}

	public static void main(String[] args) throws Exception {
		PersistenceFactoryManager pfm = PersistenceFactoryManager.getInstance();
		try {
			UserRepository uRepository = new HibernateUserRepository(pfm);
			// Main main = new Main(uRepository);
			// main.registerUser(args[0]);
		} catch (Exception e) {
			throw e;
		} finally {
			pfm.close();
		}
	}

	public void registerUser(String userName) throws Exception {
		UserRegisterCommand command = new UserRegisterCommand(userName);
		userRegisterService.handle(command);
	}

	public void updateUser(String id, String name) throws Exception {
		UserUpdateCommand updateNameCommand = new UserUpdateCommand(id) {
			{
				// BUG になるかもしれない（なんとなく使ってる）
				setName(name);
			}
		};
		// userAppService.update(updateNameCommand);
	}
}
