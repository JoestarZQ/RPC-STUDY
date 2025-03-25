package Client;

import common.message.RpcRequest;
import common.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 使用传统 IO 实现的简单客户端，用于发送请求
 */
public class IOClient {
    public static RpcResponse sendRequest(String host, int port, RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(request);
            oos.flush();

            RpcResponse response = (RpcResponse)ois.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
