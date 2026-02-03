package com.cheshun.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/***
 * @description xxxx
 * @author wangzhuo
 * @date 2022/2/20 11:30
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
