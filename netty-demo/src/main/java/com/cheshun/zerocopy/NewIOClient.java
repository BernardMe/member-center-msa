package com.cheshun.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/***
 * @description NIO 客户端
 * @author wangzhuo
 * @date 20260126
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception {

        // 8M = 8 * 1024 * 1024 字节
        long maxSize = 8 * 1024 * 1024;
        long position = 0;
        long transferTotalCount = 0;

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 7002));
        File file = new File("d:\\博客.zip");
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        long startTime = System.currentTimeMillis();

        // 在 linux 下，一个 transferTo 方法就可以完成传输
        // 在 windows 下，一次调用 transferTo 只能发送 8m，文件太大则需要分段，则需要注意传输位置
        // transferTo 底层用到了零拷贝

        // 获取文件大小，单位字节
        long fileSize = file.length();
        System.out.println("文件大小：" + fileSize + " 字节");
        long loopCount = fileSize / maxSize == 0 ? fileSize / maxSize : fileSize / maxSize + 1;
        for (long i=0; i<loopCount; i++) {
            long transferCount = fileChannel.transferTo(position, maxSize, socketChannel);
            transferTotalCount += transferCount;
            position += position;
        }

        System.out.println("发送总字节数：" + transferTotalCount + "，耗时：" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
