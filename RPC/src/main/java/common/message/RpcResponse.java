package common.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 封装的响应类，包含调用结果或错误信息
 */
@Data
@Builder
public class RpcResponse implements Serializable {
    //状态信息
    private int code;
    private String message;
    //具体数据
    private Object data;
    //构造成功信息
    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).build();
    }
    //构造失败信息（userService.getUserByUserId(id)）
    public static RpcResponse fail() {
        return RpcResponse.builder().code(500).message("服务器发生错误").build();
    }
}
