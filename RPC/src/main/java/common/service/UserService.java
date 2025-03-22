package common.service;

import common.pojo.User;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/22
 * @description ...
 */
public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
