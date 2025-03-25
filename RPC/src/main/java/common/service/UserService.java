package common.service;

import common.pojo.User;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description 用户服务接口，定义 RPC 要调用的方法
 */
public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
