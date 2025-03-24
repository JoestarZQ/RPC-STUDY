package Server.server.impl;

import Server.netty.nettyInitializer.NettyServerInitializer;
import Server.provider.ServiceProvider;
import Server.server.RpcServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description ...
 */
@AllArgsConstructor
public class NettyRpcServer implements RpcServer {
    private ServiceProvider serviceProvider;
    /*
        [main线程]
            ↓
        初始化 bossGroup / workGroup
            ↓
        配置 ServerBootstrap
            ↓
        绑定端口（bind）
            ↓
        [监听线程] bossGroup
            ↓
        接收到客户端连接
            ↓
        [客户端连接线程] workGroup
            ↓
        执行 childHandler 初始化器
            ↓
        设置 pipeline（解码器 + handler）
            ↓
        收到数据后进入 NettyRPCServerHandler 处理
    * */
    @Override
    public void start(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.println("netty服务器启动");
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(serviceProvider));//配置每个客户端连接的 handler
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();//阻塞直到启动完成
            channelFuture.channel().closeFuture().sync();//阻塞当前线程（main）直到服务器 channel 被关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {

    }
}
