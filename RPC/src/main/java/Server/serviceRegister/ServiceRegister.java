package Server.serviceRegister;

import java.net.InetSocketAddress;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description ...
 */
public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress serviceAddress);
}
