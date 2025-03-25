package Server.serviceRegister.impl;

import Server.serviceRegister.ServiceRegister;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/25
 * @description 使用 Zookeeper 实现的服务注册
 */
public class ZKServiceRegister implements ServiceRegister {
    private CuratorFramework client;
    private static final String ROOT_PATH = "JoJoRPC";
    public ZKServiceRegister() {
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
        this.client.start();
        System.out.println("Zookeeper连接成功");
    }
    @Override
    public void register(String serviceName, InetSocketAddress serviceAddress) {
        try {
            if (client.checkExists().forPath("/"+serviceName) == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/"+serviceName);
                        //这里的creatingParentsIfNeeded和creatingParentContainersIfNeeded()有什么区别？
            }
                String path = "/" + serviceName + "/" + getServiceAddress(serviceAddress);
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            System.out.println("此服务已存在");
        }
    }
    private String getServiceAddress(InetSocketAddress serverAddress) {
        return serverAddress.getHostName() + ":" + serverAddress.getPort();
    }
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }
}
