package common.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description ...
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsType;
}
