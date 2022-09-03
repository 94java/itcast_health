package top.hellocode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.hellocode.dao.PermissionDao;
import top.hellocode.dao.RoleDao;
import top.hellocode.dao.UserDao;
import top.hellocode.pojo.Permission;
import top.hellocode.pojo.Role;
import top.hellocode.pojo.User;
import top.hellocode.service.UserService;

import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 15:02
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if(user == null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        if(roles != null && roles.size() > 0){
            for (Role role : roles) {
                Integer roleId = role.getId();
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if(permissions != null && permissions.size() > 0){
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }
}
