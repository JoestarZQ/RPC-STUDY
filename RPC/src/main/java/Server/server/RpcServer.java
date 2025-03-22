package Server.server;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description ...
 */
public interface RpcServer {
    void start(int port);
    void stop();
}
