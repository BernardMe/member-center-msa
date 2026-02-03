package com.cheshun.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/***
 * 一个英文字符占 1 个字节，一个汉字占 3 个字节，符号（/n、/t等）占 2 个字节
 * @author wangzhuo
 * @date 20260126
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {
        // 创建 ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 创建 Selector
        Selector selector = Selector.open();

        // 绑定端口 8888
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));

        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把 serverSocketChannel 注册到 selector，关心 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while(true) {

            // selector.select 返回就绪SocketChannel的个数；事件可能是连接，也可能是读写
            while(selector.select(1000) == 0) {
                System.out.println("服务器等待了 1 秒，无就绪 SocketChannel");
                continue;
            }

            // 有就绪的 SocketChannel，则处理它们的事件
            // selector.selectedKeys() 返回的就是就绪的 SocketChannel 集合
            // SelectionKey -> 事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // OP_ACCEPT 事件，有新的 client 连接
                if (key.isAcceptable()) {
                    // 为新 client 生成新的 SocketChannel
                    // 事件驱动，说明已经有连接来了，那么 accept() 就不会阻塞
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("客户单连接成功 socketChannel.hashCode = " + socketChannel.hashCode());

                    // 将 socketChannel 注册到 selector，并关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                // OP_READ 事件，有 SocketChannel 的数据就绪了
                if (key.isReadable()) {
                    // 通过 key 获取就绪的 SocketChannel
                    SocketChannel socketChannel  = (SocketChannel) key.channel();
                    // 获取该SocketChannel关联的Buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端：" + new String(byteBuffer.array()));
                }

                // 移除当前处理完成的 SelectionKey，防止重复处理
                keyIterator.remove();
            }
        }
    }
}
