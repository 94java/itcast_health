package top.hellocode.dao;

import top.hellocode.pojo.Role;

import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 15:06
 */
public interface RoleDao {
    public Set<Role> findByUserId(Integer userId);
}
