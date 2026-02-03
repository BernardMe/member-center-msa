package com.cheshun.netty.sample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/***
 * @description netty 客户端
 * @author wangzhuo
 * @date 20260126
 */
public class NettyClient {

    public static void main(String[] args) throws Exception {
        // 客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)     // 设置线程组
                    .channel(NioSocketChannel.class)    // 设置客户端 channel 的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("......客户端 ok......");

            // 启动客户端去连接服务器端
            // 关于 ChannelFuture，涉及到 netty 的异步模型
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 7778).sync();

            // 给关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
