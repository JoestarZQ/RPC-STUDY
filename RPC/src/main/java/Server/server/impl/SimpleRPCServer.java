package Server.server.impl;

import lombok.AllArgsConstructor;
import Server.provider.ServiceProvider;
import Server.server.RpcServer;
import Server.server.work.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 使用传统 IO 实现的简单 RPC 服务端
 */
@AllArgsConstructor
public class SimpleRPCServer implements RpcServer {
    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器启动了");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
