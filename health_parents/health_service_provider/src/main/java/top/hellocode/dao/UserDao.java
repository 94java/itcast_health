package top.hellocode.dao;

import top.hellocode.pojo.User;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 15:03
 */
public interface UserDao {
    public User findByUsername(String username);
}
