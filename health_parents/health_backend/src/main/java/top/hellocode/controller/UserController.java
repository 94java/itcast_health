package top.hellocode.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.Result;

import javax.ws.rs.core.SecurityContext;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月29日 15:29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername()throws Exception{
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
