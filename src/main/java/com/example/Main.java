package com.example;

import com.example.Base.Domain.Entity.User;
import com.example.Base.Domain.Repository.UserRepository;
import com.example.Base.Domain.Service.UserService;
import com.example.Base.Domain.ValueObject.UserName;
import com.example.Base.Infrastructure.HibernateUserRepository;
import com.example.Base.Infrastructure.PersistenceFactoryManager;
import com.example.Base.app.users.UserAppService;
import com.example.Base.app.users.UserUpdateCommand;

public class Main {
	private final UserRepository userRepository;
	private final UserService userService;
	private final UserAppService userAppService;

	public Main(UserRepository uRepository) {
		userRepository = uRepository;
		userService = new UserService(uRepository);
		userAppService = new UserAppService(uRepository, userService);
	}

	public static void main(String[] args) throws Exception {
		PersistenceFactoryManager pfm = PersistenceFactoryManager.getInstance();
		try {
			UserRepository uRepository = new HibernateUserRepository(pfm);
			Main main = new Main(uRepository);
			main.createUser(args[0]);
		} catch (Exception e) {
			throw e;
		} finally {
			pfm.close();
		}
	}

	// TODO 削除（UserAppService#registerに移動）
	public void createUser(String userName) throws Exception {
		User user = new User(new UserName(userName));
		if (userService.Exists(user)) {
			throw new Exception(userName + "はすでに存在しています。");
		}
		userRepository.save(user);
	}

	public void updateUser(String id, String name) throws Exception {
		UserUpdateCommand updateNameCommand = new UserUpdateCommand(id) {
			{
				// BUG になるかもしれない（なんとなく使ってる）
				setName(name);
			}
		};
		userAppService.update(updateNameCommand);
	}
}
