package netty.timer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by wangtian9 on 2017/7/26.
 */
public class TimeClient {
    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes())));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ChannelHandlerAdapter() {
                                private byte[] req = "query time order$_".getBytes();

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ByteBuf message = null;
                                    for (int i = 0; i < 10; i++) {
                                        message = Unpooled.buffer(req.length);
                                        message.writeBytes(req);
                                        ctx.writeAndFlush(message);
                                    }
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    String body = (String) msg;
                                    System.out.println(body);
                                }

                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                    ctx.flush();
                                }
                            });
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new TimeClient().connect(10002, "127.0.0.1");
    }
}
