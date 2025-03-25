package Client.rpcClient.impl;

import Client.rpcClient.RpcClient;
import common.message.RpcRequest;
import common.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description 使用传统 Socket 实现的简单 RPC 客户端
 */
public class SimpleSocketRpcClient implements RpcClient {
    private String host;
    private int port;
    public SimpleSocketRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(rpcRequest);
            oos.flush();

            RpcResponse response = (RpcResponse)ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
