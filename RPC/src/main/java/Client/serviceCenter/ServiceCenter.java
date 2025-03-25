package Client.serviceCenter;

import java.net.InetSocketAddress;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description 服务发现接口，定义如何获取服务地址
 */
public interface ServiceCenter {
    InetSocketAddress serviceDiscovery(String serviceName);
}
