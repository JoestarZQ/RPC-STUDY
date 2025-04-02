package Client.serviceCenter.zkWatcher;

import Client.cache.ServiceCache;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/4/1
 * @description ...
 */
public class WatchZK {
    private CuratorFramework client;
    ServiceCache cache;
    public WatchZK(CuratorFramework client, ServiceCache cache) {
        this.client = client;
        this.cache = cache;
    }
    public void watchToUpdate(String path) throws InterruptedException {
        CuratorCache curatorCache = CuratorCache.build(client, "/");
        curatorCache.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData childData, ChildData childData1) {
                switch (type.name()) {
                    case "NODE_CREATED":
                        String[] pathList = parsePath(childData1);
                        if (pathList.length <= 2) break;
                        else {
                            String serviceName = pathList[1];
                            String address = pathList[2];
                            cache.addServiceToCache(serviceName, address);
                        }
                        break;
                    case "NODE_CHANGED":
                        if (childData.getData() != null)
                            System.out.println("修改前的数据：" + new String(childData.getData()));
                        else
                            System.out.println("节点第一次赋值");
                        String[] oldPathList = parsePath(childData);
                        String[] newPathList = parsePath(childData1);
                        cache.replaceServiceAddress(oldPathList[1], oldPathList[2], newPathList[2]);
                        System.out.println("修改后的数据：" + new String(childData1.getData()));
                        break;
                    case "NODE_DELETED":
                        String[] pathList_d = parsePath(childData);
                        if (pathList_d.length <= 2) break;
                        else {
                            String serviceName = pathList_d[1];
                            String address = pathList_d[2];
                            cache.delete(serviceName, address);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        curatorCache.start();
    }
    public String[] parsePath(ChildData childData) {
        String path = new String(childData.getData());
        return path.split("/");
    }
}
