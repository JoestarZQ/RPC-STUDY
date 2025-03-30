package common.serializer.mySerializer;

import common.serializer.mySerializer.impl.JsonSerializer;
import common.serializer.mySerializer.impl.ObjectSerializer;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/29
 * @description ...
 */
public interface Serializer {
    //把对象序列化为字节数组
    byte[] serialize(Object obj);
    //将一个字节数组反序列化为Java对象
    //如果用Java自带的不用messageType也能得到相应的对象
    //其他方式需要指定消息格式，再根据messageType转化成消息对象
    Object deserialize(byte[] bytes, int messageType);
    //0为Java自带的序列化方式，1为json
    int getType();
    //静态工厂方法
    //根据序号取出序列化器，暂时有两种实现方式，需要其他方式，实现这个接口即可
    static Serializer getSerializerByCode(int code) {
        switch (code) {
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }

}
