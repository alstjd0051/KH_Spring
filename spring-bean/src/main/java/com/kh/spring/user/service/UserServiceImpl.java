package com.kh.spring.user.service;

import org.springframework.stereotype.Component;

import com.kh.spring.user.model.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public void getUserDetail(String id) {
		System.out.println("사용자 " + id + "의 이름은 송민성입니다.");
	}

}
