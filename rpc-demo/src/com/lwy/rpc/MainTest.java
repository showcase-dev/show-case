package com.lwy.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.lwy.rpc.core.client.SocketRPCClient;
import com.lwy.rpc.service.HelloService;

public class MainTest {

	public static void main(String[] args) throws IOException {
		Long time=System.currentTimeMillis();
		HelloService helloService = SocketRPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
//		HelloService helloService =new HelloServiceImpl();
		System.out.println((System.currentTimeMillis()-time)+"ms");
		
		System.out.println(helloService.say("zhangsan"));
	}

}
