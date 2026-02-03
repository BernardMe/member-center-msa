package com.cheshun.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * @description xxxx
 * @author wangzhuo
 * @date 20260126
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception {

        // 创建一个输出流
        FileOutputStream fileOutStream = new FileOutputStream("d:\\1.txt");

        // 通过输出流获取 channel
        FileChannel fileChannel = fileOutStream.getChannel();

        // 创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 往缓存区写数据
        byteBuffer.put("hello, wangzhuo".getBytes());

        // 反转buffer
        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        // 重置buffer
        // byteBuffer.clear();

        // 获取buffer的 final byte[] hb;
        // byteBuffer.array();

        fileChannel.close();
        fileOutStream.close();
    }
}
