package com.cheshun.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/***
 * @description xxxx
 * @author wangzhuo
 * @date 20260126
 */
public class MyServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 基于 http
                            pipeline.addLast(new HttpServerCodec());
                            // 以块方式写
                            pipeline.addLast(new ChunkedWriteHandler());

                            /**
                             * http数据在传输过程中是分段，HttpObjectAggregator可以将多个段进行聚合
                             * 当浏览器发送大量数据时，就会发出多次http请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /**
                             * 1、websocket的数据是以 帧（frame）形式传递
                             * 2、WebSocketFrame 有六个子类
                             * 3、浏览器请求时 ws://localhost:6668/hello 表示请求的 uri
                             * 4、WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws协议，保持长连接（通过状态码 101 实现）
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            // 添加自定义的 handler，进行业务处理
                            pipeline.addLast(null);
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(6668).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
