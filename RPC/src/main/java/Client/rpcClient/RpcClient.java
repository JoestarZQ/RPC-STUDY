package Client.rpcClient;

import common.message.RpcRequest;
import common.message.RpcResponse;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description 客户端 RPC 接口，定义了发送请求的基本方法
 */
public interface RpcClient {
    //定义底层通信方法
    RpcResponse sendRequest(RpcRequest rpcRequest) throws ClassNotFoundException;
}
