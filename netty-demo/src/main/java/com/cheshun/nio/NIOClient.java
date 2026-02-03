package com.cheshun.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/***
 * @description xxxx
 * @author wangzhuo
 * @date 20260126
 */
public class NIOClient {

    public static void main(String[] args) throws Exception {
        // 创建 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务器信息
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8888);

        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("正在连接服务器，客户端不会阻塞");
            }
        }

        // 连接服务器成功
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello, wangzhuo".getBytes());
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
