package com.luosu.netty;

import android.content.Context;
import android.util.Log;

import com.luosu.listener.NettyHnalerListener;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by User on 2017/3/30.
 */

public class NettyClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static final int PORT = 8585;
    public static final String IP = "192.168.3.66";
    public static EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    private Context context;
    NettyHnalerListener listener;
    private Channel channel;
    private static final String LOGTAG = "NettyClient";

    public NettyClient(Context context, NettyHnalerListener listener) {
        this.context = context;
        this.listener = listener;
    }


    public void connect(final int port, final String ip, final int index) {
        new Thread() {
            @Override
            public void run() {
                try

                {
                    Bootstrap b = new Bootstrap();
                    b.group(nioEventLoopGroup).channel(NioSocketChannel.class)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    ChannelPipeline pipline = socketChannel.pipeline();
                                    pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                                    pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                                    NettyClientHnadler handler = new NettyClientHnadler(context, index);
                                    handler.setListener(NettyClient.this.listener);
                                    pipline.addLast(handler);
                                }
                            });


                    ChannelFuture future = b.connect(new InetSocketAddress(ip, port)).sync();
                    ;     if (future.isSuccess()) {
                    Log.d(LOGTAG, "连接成功");

                    channel = future.channel();
                } else {
                    Log.d(LOGTAG, "连接失败");
                }
                    future.channel().closeFuture().sync();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                                connect(PORT, IP, index);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }


        }.start();

    }

    public void sendMessage(String msg) {//连接成功后，通过Channel提供的接口进行IO操作
        try {
            if (channel != null && channel.isOpen()) {
                channel.writeAndFlush(msg).sync();     //(1)
                Log.d(LOGTAG, "send succeed " + msg);
            } else {
                Log.d(LOGTAG, "没有打开");
                throw new Exception("channel is null | closed");
            }
        } catch (Exception e) {
//            sendReconnectMessage();
            e.printStackTrace();
        }
    }

}
