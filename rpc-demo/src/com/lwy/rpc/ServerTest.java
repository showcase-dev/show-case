package com.lwy.rpc;

import java.io.IOException;

import com.lwy.rpc.core.server.Server;
import com.lwy.rpc.core.server.impl.SocketServer;
import com.lwy.rpc.service.HelloService;
import com.lwy.rpc.service.impl.HelloServiceImpl;

public class ServerTest {
	
	public static void main(String[] args) throws Exception, InterruptedException {
		Server server = new SocketServer(8088);
		server.register(HelloService.class, HelloServiceImpl.class);
		server.start();
		Thread.sleep(1000);
		server.stop();
	}
}
