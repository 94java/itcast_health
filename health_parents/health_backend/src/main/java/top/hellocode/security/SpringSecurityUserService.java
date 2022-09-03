package top.hellocode.security;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.hellocode.pojo.Permission;
import top.hellocode.pojo.Role;
import top.hellocode.pojo.User;
import top.hellocode.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 14:55
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference //注意：此处要通过dubbo远程调用用户服务
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 1. 导包
         * 2. web.xml配置filter
         * 3. 创建对应的UserService在数据库查询数据
         * 4. 配置springxml文件
         * */

        User user = userService.findByUsername(username);
        if(user == null){
            // 用户不存在
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<>();
        // 为用户授权
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                // 授权权限
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return userDetails;
    }
}
