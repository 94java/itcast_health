package top.hellocode.dao;

import top.hellocode.pojo.Permission;

import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 15:07
 */
public interface PermissionDao {
    public Set<Permission> findByRoleId(Integer roleId);
}
