package netty.timer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;

/**
 * Created by wangtian9 on 2017/7/26.
 */
public class TimerServer {
    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 1024).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,Unpooled.copiedBuffer("$_".getBytes())));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ChannelHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    String body = (String) msg;
                                    System.out.println(body);
                                    String currentTime = "query time order".equals(body) ? new Date(System.currentTimeMillis()).toString()+"$_" : "bad order";
                                    ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
                                    ctx.writeAndFlush(resp);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new TimerServer().bind(10002);
    }
}
