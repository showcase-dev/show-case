package com.lwy.rpc.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.lwy.rpc.core.req.Request;

@SuppressWarnings("all")
public class NettyRPCClient {
	public static <T> T getRemoteProxyObj(final Class<?> serviceInterface,
			final InetSocketAddress addr) {
		// 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),new Class<?>[] { serviceInterface }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						//请求
						Request request=new Request();
						request.setServiceName(serviceInterface.getName());
						request.setMethodName(method.getName());
						request.setParameterTypes(method.getParameterTypes());
						request.setArgs(args);
						try{
							
						}finally{
							
						}
						EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
						 Bootstrap bootstrap = new Bootstrap();
						 bootstrap.group(eventLoopGroup)
						 	.channel(NioServerSocketChannel.class);
						 Channel channel = bootstrap.connect("localhost",addr.getPort()).sync().channel();
						 channel.write(request);
						 channel.flush();
						 


						return null;
					}
				});
	}
}
