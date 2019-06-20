import factory.ProxyFactory;
import aop.Aop;
import service.UserService;
import service.impl.UserServiceImpl;



public class MainTest {
	public static void main(String[] args) {
		Aop aop=new Aop();
		UserService userService=(UserService) ProxyFactory.newProxyInstance(new UserServiceImpl(), aop);
		userService.addUser();
	}
}
