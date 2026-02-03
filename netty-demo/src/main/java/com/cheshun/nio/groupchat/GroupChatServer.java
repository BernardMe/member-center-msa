package com.cheshun.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/***
 * @description 群聊服务端
 * @author wangzhuo
 * @date 20260126
 */
public class GroupChatServer {

    private static final int PORT = 8887;

    private Selector selector;
    private ServerSocketChannel listenChannel;

    /**
     * 初始化相关参数
     */
    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while (true) {
                // 没有就绪事件，阻塞
                int count = selector.select();
                if (count > 0) {
                    // 有就绪事件

                    // 拿到全局就绪的事件，逐个进行事件处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        // accept 事件
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            // 上线提示
                            System.out.println(sc.getRemoteAddress() + " 上线 ");
                        }

                        // read 事件
                        if(key.isReadable()) {
                            readData(key);
                        }

                        // 事件处理完之后，移除它
                        iterator.remove();
                    }
                } else {
                    // 没有就绪事件
                    System.out.println("没有就绪事件，等待...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端信息
     * @param key
     */
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if (count > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端：" + msg);

                // 向其它客户端转发消息
                sendMsgToOtherClient(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了...");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 向其它客户端转发消息
     * @param msg
     * @param self
     */
    private void sendMsgToOtherClient(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        for(SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
