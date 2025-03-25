package Server;


import Server.provider.ServiceProvider;
import Server.server.RpcServer;
import Server.server.impl.NettyRpcServer;
import Server.server.impl.SimpleRPCServer;
import common.service.UserService;
import common.service.impl.UserServiceImpl;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 启动服务端进行测试的入口类
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 9998);
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
        rpcServer.start(9998);
    }
}
