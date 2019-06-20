package com.lwy.rpc.core.server.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lwy.rpc.core.server.Server;
import com.lwy.rpc.core.server.handle.SocketServerHandle;
import com.lwy.rpc.core.server.registry.ServiceRegistry;

public class SocketServer implements Server {

	private static ExecutorService executor = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private static volatile boolean isRunning = false;

	private static int port;

	private ServerSocket server;

	public SocketServer(int port) {
		this.port = port;
	}

	public SocketServer() {
	}

	@Override
	public void stop() throws IOException {
		isRunning = false;
		server.close();
		executor.shutdownNow();
		System.out.println("server stoped");
	}

	@Override
	public void start() throws IOException {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("start server");
				isRunning = true;
				try {
					server = new ServerSocket();
					server.bind(new InetSocketAddress(port));
					while (true) {
						executor.execute(new SocketServerHandle(server.accept()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void register(Class serviceInterface, Class impl) {
		ServiceRegistry.add(serviceInterface.getName(), impl);
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPort() {
		return port;
	}

}
