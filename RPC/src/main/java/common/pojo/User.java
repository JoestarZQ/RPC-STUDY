package common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description ...
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    //客户端和服务端公用的
    private Integer id;
    private String userName;
    private Boolean sex;
}
