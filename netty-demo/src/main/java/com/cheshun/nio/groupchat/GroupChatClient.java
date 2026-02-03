package com.cheshun.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/***
 * @description 群聊客户端
 * @author wangzhuo
 * @date 20260126
 */
public class GroupChatClient {

    /**
     * 服务端信息
     */
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8887;

    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    /**
     * 初始化
     */
    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("socketChannel：" + socketChannel.hashCode());
            System.out.println(userName + " 连接成功 ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向服务器发送消息
     * @param msg
     */
    public void sendMsg(String msg) {
        msg = userName + " 说：" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取从服务器回复的消息
     */
    public void readMsg() {
        try {
            int readChannels = selector.select();
            if (readChannels > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();

                        // 注意多个客户端的此处输出
                        System.out.println("socketChannel：" + sc.hashCode());
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg);
                    }
                }
                // 事件处理完之后，移除它
                iterator.remove();
            } else {
                // System.out.println("没有就绪的事件，等待...");
            }
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();

        // 启动一个线程，每隔 3 秒读一次从服务器发送过来的消息
        new Thread(() -> {
            while (true) {
                client.readMsg();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送消息给服务器
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            client.sendMsg(msg);
        }
    }
}
