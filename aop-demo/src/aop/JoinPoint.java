package aop;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class JoinPoint {

	private Method method;
	private Parameter[] params;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Parameter[] getParams() {
		return params;
	}

	public void setParams(Parameter[] params) {
		this.params = params;
	}

}
