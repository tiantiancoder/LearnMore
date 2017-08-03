package netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import netty.timer.TimerServer;

import java.util.Date;

/**
 * Created by wangtian9 on 2017/7/27.
 */
public class SubReqServer {
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
                            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            socketChannel.pipeline().addLast(new ProtobufDecoder(StudentProto.Student.getDefaultInstance()));
                            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            socketChannel.pipeline().addLast(new ProtobufEncoder());
                            socketChannel.pipeline().addLast(new ChannelHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    StudentProto.Student body = (StudentProto.Student) msg;
                                    System.out.println(body.getId());
                                    TeacherProto.Teacher.Builder teacher=TeacherProto.Teacher.newBuilder();
                                    teacher.setAge(33);
                                    teacher.setId(2);
                                    teacher.setName("王五");
                                    ctx.writeAndFlush(teacher.build());
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
        new SubReqServer().bind(10002);
    }
}
