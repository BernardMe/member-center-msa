package com.cheshun.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/***
 * @description NIO 服务器
 * @author wangzhuo
 * @date 20260126
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7002);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            long totalCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                    totalCount += readCount;
                } catch (Exception e) {
                    // e.printStackTrace();
                    break;
                }
                // 倒带 position = 0， mark 作废
                byteBuffer.rewind();
            }
            System.out.println("接收总字节数：" + totalCount);
        }
    }
}
