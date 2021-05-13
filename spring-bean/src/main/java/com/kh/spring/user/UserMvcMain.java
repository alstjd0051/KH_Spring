package com.kh.spring.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kh.spring.user.controller.UserController;

public class UserMvcMain {

	public static void main(String[] args) {
		String configLocation = "/application-context-with-annotation.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		System.out.println("---------- spring-container bean �ʱ�ȭ �Ϸ� ----------");
		
		UserController controller1 = context.getBean(UserController.class);
		controller1.getUserDetail("miiin_sseong");
		
		UserController controller2 = context.getBean(UserController.class);
		controller2.getUserDetail("alstjd0051");
		
		System.out.println(controller1);
		System.out.println(controller2);
		System.out.println(controller1 == controller2);
		
	}

}