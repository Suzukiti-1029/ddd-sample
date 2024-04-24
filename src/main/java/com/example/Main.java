package com.example;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.base.app.users.UserRegisterCommand;
import com.example.base.app.users.UserRegisterService;
import com.example.base.app.users.impl.UserRegisterServiceImpl;
import com.example.base.domain.repository.UserRepository;
import com.example.base.domain.service.UserService;
import com.example.base.infrastructure.HibernateUserRepository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	private static final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	public static void main(String[] args) {
		startup();
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("Input user name");
				System.out.print(">");
				String input = scanner.nextLine();
				// 登録処理
				UserRegisterService userRegisterService = ctx.getBean(UserRegisterService.class);
				UserRegisterCommand userRegisterCommand = new UserRegisterCommand(input);
				userRegisterService.handle(userRegisterCommand);

				System.out.println("--------------------");
				System.out.println("user created:");
				System.out.println("--------------------");
				System.out.println("user name:");
				System.out.println("- " + input);
				System.out.println("--------------------");

				System.out.println("continue? (y/n)");
				System.out.print(">");
				String yesOrNo = scanner.nextLine();
				if (StringUtils.equals(yesOrNo, "n"))
					break;
			}
		} catch (Exception e) {
			System.err.print("エラーが起きました: ");
			System.err.println(e.getMessage());
		} finally {
			scanner.close();
			ctx.getBean(EntityManagerFactory.class).close();
			ctx.close();
		}
	}

	private static void startup() {
		ctx.registerBean(EntityManagerFactory.class,
				() -> Persistence.createEntityManagerFactory("myPU"),
				bd -> bd.setScope(BeanDefinition.SCOPE_SINGLETON));
		ctx.registerBean(UserRepository.class,
				() -> new HibernateUserRepository(
						ctx.getBean(EntityManagerFactory.class)),
				bd -> bd.setScope(BeanDefinition.SCOPE_SINGLETON));
		ctx.registerBean(UserService.class,
				bd -> bd.setScope(BeanDefinition.SCOPE_PROTOTYPE));
		ctx.registerBean(UserRegisterService.class,
				() -> new UserRegisterServiceImpl(
						ctx.getBean(UserRepository.class),
						ctx.getBean(UserService.class)),
				bd -> bd.setScope(BeanDefinition.SCOPE_PROTOTYPE));
		ctx.refresh();
	}
}
