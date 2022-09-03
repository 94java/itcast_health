package top.hellocode.service;

import top.hellocode.pojo.User;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 14:56
 */
public interface UserService {
    public User findByUsername(String username);
}
