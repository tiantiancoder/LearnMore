package netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import netty.timer.TimeClient;

/**
 * Created by wangtian9 on 2017/7/27.
 */
public class SubReqClient {
    public void connect(int port,String host) throws Exception{
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY,true).
                    handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            socketChannel.pipeline().addLast(new ProtobufDecoder(TeacherProto.Teacher.getDefaultInstance()));
                            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            socketChannel.pipeline().addLast(new ProtobufEncoder());
                            socketChannel.pipeline().addLast(new ChannelHandlerAdapter(){

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    StudentProto.Student.Builder builder= StudentProto.Student.newBuilder();
                                    builder.setName("李四");
                                    builder.setAge(12);
                                    builder.setId(1);
                                    ctx.writeAndFlush(builder.build());

                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    TeacherProto.Teacher teacher=(TeacherProto.Teacher)msg;
                                    System.out.print(teacher.getName());
                                }

                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                    ctx.flush();
                                }
                            });
                        }
                    });
            ChannelFuture f=b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new SubReqClient().connect(10002,"127.0.0.1");
    }
}
