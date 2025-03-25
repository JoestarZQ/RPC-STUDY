package Server.netty.nettyInitializer;

import Server.netty.handler.NettyServerHandler;
import Server.provider.ServiceProvider;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description Netty 服务端初始化器，配置 pipeline、编解码器等
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;
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
        pipeline.addLast(new NettyServerHandler(serviceProvider));
    }
}
