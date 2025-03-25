package Server.provider;

import Server.serviceRegister.ServiceRegister;
import Server.serviceRegister.impl.ZKServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 服务注册接口，用于将本地服务注册到服务注册中心
 */
public class ServiceProvider {
    private Map<String, Object> interfaceProvider;
    private String host;
    private int port;
    private ServiceRegister serviceRegister;
    public ServiceProvider(String host, int port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        serviceRegister = new ZKServiceRegister();
    }
    public void provideServiceInterface(Object service) {
        String serviceName = service.getClass().getName();
        Class<?>[] interfaceName = service.getClass().getInterfaces();

        for (Class<?> clazz : interfaceName) {
            interfaceProvider.put(clazz.getName(), service);
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host, port));
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
