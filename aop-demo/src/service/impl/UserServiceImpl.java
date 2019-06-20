package service.impl;
import service.UserService;
import annotation.MethodAnnotation;
import annotation.ParamsAnnotation;

public class UserServiceImpl implements UserService {
	@Override
	public void addUser() {
		System.out.println("add user");
	}

	@Override
	
	public void addUser(@ParamsAnnotation("用户名") String username) {
		System.out.println("add " + username);

	}
}
