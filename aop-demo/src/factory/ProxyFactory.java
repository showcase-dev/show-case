package factory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import aop.Aop;
import aop.JoinPoint;

public class ProxyFactory {
	
	public static Object newProxyInstance(final Object target, final Aop aop) {
		// 生成代理对象的方法
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						
						JoinPoint jsonPoint=new JoinPoint();
						jsonPoint.setParams(method.getParameters());
						jsonPoint.setMethod(method);
						aop.begin(jsonPoint);
						Object result = method.invoke(target, args);// 执行目标对象的方法
						aop.end(jsonPoint,result);// 关注点代码
						return result;
					}
				});
	}
}
