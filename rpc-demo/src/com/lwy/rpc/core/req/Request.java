package com.lwy.rpc.core.req;

import java.io.Serializable;

public class Request implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String reqId;
	
	private String serviceName;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] args;
	
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
