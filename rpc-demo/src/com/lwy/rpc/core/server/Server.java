package com.lwy.rpc.core.server;

import java.io.IOException;

/**
 * 定义服务接口
 * 
 * @author lishiwen
 *
 */
public interface Server {
	public void stop() throws  Exception ;

	public void start() throws Exception;

	public void register(Class serviceInterface, Class impl);

	public boolean isRunning();

	public int getPort();
}
