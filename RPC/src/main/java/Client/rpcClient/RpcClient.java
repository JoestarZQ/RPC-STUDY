package Client.rpcClient;

import common.message.RpcRequest;
import common.message.RpcResponse;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/24
 * @description ...
 */
public interface RpcClient {
    //定义底层通信方法
    RpcResponse sendRequest(RpcRequest rpcRequest) throws ClassNotFoundException;
}
