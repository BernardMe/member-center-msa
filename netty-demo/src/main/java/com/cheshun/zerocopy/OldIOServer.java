package com.cheshun.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * @description 传统 io 服务端
 * @author wangzhuo
 * @date 20260126
 */
public class OldIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArray = new byte[4096];

                while (true) {
                    int readCount = dataInputStream.read(byteArray, 0, byteArray.length);
                    if (-1 == readCount) {
                        System.out.println("此次读取完毕");
                        break;
                    }
                    System.out.println("此次读取 " + readCount + " 字节内容");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
