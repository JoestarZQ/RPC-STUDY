package Client.serviceCenter.impl;

import Client.serviceCenter.ServiceCenter;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description 使用 Zookeeper 实现的服务发现
 */
public class ZKServiceCenter implements ServiceCenter {
    private CuratorFramework client;
    private static final String ROOT_PATH = "JoJoRPC";
    public ZKServiceCenter() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
        this.client.start();
        System.out.println("zookeeper连接成功");
    }
    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        try {
            List<String> strings = client.getChildren().forPath("/" + serviceName);
            String string = strings.get(0);
            return parseAddress(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getServiceAddress(InetSocketAddress serverAddress) {
        return serverAddress.getHostName() + ":" + serverAddress.getPort();
    }
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }
}
