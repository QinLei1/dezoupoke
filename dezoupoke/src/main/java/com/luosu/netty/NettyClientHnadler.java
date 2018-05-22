package com.luosu.netty;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.luosu.listener.NettyHnalerListener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by User on 2017/3/30.
 */

public class NettyClientHnadler extends ChannelInboundHandlerAdapter {
    public int index = 0;
    private Context context;
    NettyHnalerListener listener;

    public NettyClientHnadler(Context context, int index) {
        this.index = index;
        this.context = context;
    }

    public void setListener(NettyHnalerListener listener) {
        this.listener = listener;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Log.d("netty", "消息为msg:" + msg.toString());
//        Toast.makeText(context, "消息为:" + msg.toString(), Toast.LENGTH_SHORT).show();
        listener.channelReadListener(msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
