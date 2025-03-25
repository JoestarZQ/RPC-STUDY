package Client.netty.nettyInitializer;

import Client.netty.handler.NettyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description Netty 客户端通道初始化器，配置编码器、解码器和 handler
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    /*
    入站数据流：
        ByteBuf（二进制） → 解包（LengthFieldBasedFrameDecoder）
        → 反序列化（ObjectDecoder） → RpcResponse → NettyClientHandler.channelRead0()

    出站数据流：
        RpcRequest → 序列化（ObjectEncoder）
        → 加长度前缀（LengthFieldPrepender） → ByteBuf 发送

     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //|----4字节长度----|----消息内容（字节）----|
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        //|长度（4字节）|真正消息内容|
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return Class.forName(className);
            }
        }));
        pipeline.addLast(new NettyClientHandler());
    }
}
