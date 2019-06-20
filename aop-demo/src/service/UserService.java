package service;
import annotation.MethodAnnotation;
import annotation.ParamsAnnotation;

public interface UserService {

	void addUser();
	
	@MethodAnnotation("添加用户")
	void addUser(@ParamsAnnotation("用户名") String username);
	
}
