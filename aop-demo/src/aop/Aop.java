package aop;
import annotation.MethodAnnotation;

public class Aop {
	// 重复执行的代码
	public void begin() {
		System.out.println("事务开启");
	}

	// 重复执行的代码
	public void end() {
		System.out.println("事务结束");
	}

	public void end(Object result) {
		System.out.println("invoke result:"+result);
		
	}

	public void begin(Object[] args) {
		System.out.println("invoke params:"+args[0]);
		
	}

	public void begin(JoinPoint jsonPoint) {
		boolean isAnnotationPresent=jsonPoint.getMethod().isAnnotationPresent(MethodAnnotation.class);
		System.out.println("invoke method:"+jsonPoint.getMethod().getName());
		System.out.println("isAnnotationPresent:"+isAnnotationPresent);
		if(isAnnotationPresent){
			MethodAnnotation methodAnnotation=jsonPoint.getMethod().getAnnotation(MethodAnnotation.class);
			System.out.println("methodAnnotation name:"+methodAnnotation.value());
		}
		if(jsonPoint.getParams().length>0){
			System.out.println("invoke params type:"+jsonPoint.getParams()[0].getType());
			System.out.println("invoke params name:"+jsonPoint.getParams()[0].getName());
		}
		
	}

	public void end(JoinPoint jsonPoint, Object result) {
		// TODO Auto-generated method stub
		System.out.println("result:"+result);
		
	}
}
