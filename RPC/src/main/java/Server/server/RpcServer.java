package Server.server;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 服务端接口，定义启动 RPC 服务的基本行为
 */
public interface RpcServer {
    void start(int port);
    void stop();
}
