package Client.serviceCenter;

import java.net.InetSocketAddress;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description ...
 */
public interface ServiceCenter {
    InetSocketAddress serviceDiscovery(String serviceName);
}
