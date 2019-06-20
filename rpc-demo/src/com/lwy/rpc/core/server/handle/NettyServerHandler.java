package com.lwy.rpc.core.server.handle;

import java.lang.reflect.Method;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.lwy.rpc.core.req.Request;
import com.lwy.rpc.core.server.registry.ServiceRegistry;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof Request) {
			Request reqeust = (Request) msg;
			Class serviceClass = ServiceRegistry.get(reqeust.getServiceName());
			Method method = serviceClass.getMethod(reqeust.getMethodName(),reqeust.getParameterTypes());
			Object object = method.invoke(serviceClass, reqeust.getArgs());
			ctx.writeAndFlush(object);
		}
		super.channelRead(ctx, msg);
	}

}
