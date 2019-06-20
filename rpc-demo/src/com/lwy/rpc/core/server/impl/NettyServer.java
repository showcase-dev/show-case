package com.lwy.rpc.core.server.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.nio.charset.Charset;

import com.lwy.rpc.core.server.Server;
import com.lwy.rpc.core.server.handle.NettyServerHandler;
import com.lwy.rpc.core.server.registry.ServiceRegistry;

public class NettyServer implements Server {

	private EventLoopGroup bossGroup;

	private EventLoopGroup workGroup;

	private static volatile boolean isRunning = false;

	private static int port;

	@Override
	public void stop() throws Exception {
		System.out.println("server stoped");
		isRunning = false;
		bossGroup.shutdownGracefully().sync();
		workGroup.shutdownGracefully().sync();
	}

	@Override
	public void start() throws IOException, InterruptedException {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		bossGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup();
		NettyServerHandler handle=new NettyServerHandler();
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		serverBootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class).localAddress(this.port)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						System.out.println("报告");
						System.out.println("信息：有一客户端链接到本服务端");
						System.out.println("IP:"
								+ ch.localAddress().getHostName());
						System.out.println("Port:"
								+ ch.localAddress().getPort());
						System.out.println("报告完毕");

						ch.pipeline().addLast(
								new StringEncoder(Charset.forName("UTF-8")));
						ch.pipeline().addLast(handle); // 客户端触发操作
						ch.pipeline().addLast(new ByteArrayEncoder());
					}
				});
		ChannelFuture cf = serverBootstrap.bind().sync(); // 服务器异步创建绑定
		cf.channel().closeFuture().sync(); // 关闭服务器通道
		isRunning = true;
		System.out.println("server started");
	}

	@Override
	public void register(Class serviceInterface, Class impl) {
		ServiceRegistry.add(serviceInterface.getName(), impl);
	}

	@Override
	public boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public int getPort() {
		return this.port;
	}

}
