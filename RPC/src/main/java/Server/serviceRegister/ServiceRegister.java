package Server.serviceRegister;

import java.net.InetSocketAddress;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description 服务注册接口，定义如何将服务注册到注册中心
 */
public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress serviceAddress);
}
