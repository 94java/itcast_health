package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import top.hellocode.constant.MessageConstant;
import top.hellocode.constant.RedisMessageConstant;
import top.hellocode.entity.Result;
import top.hellocode.pojo.Member;
import top.hellocode.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月28日 10:48
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/login")
    public Result login(HttpServletResponse resp, @RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        // 校验验证码是否正确
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if(validateCode != null && validateCodeInRedis != null && validateCodeInRedis.equals(validateCode)){
            // 验证码输入正确
            // 判断是否是新会员，新会员自动注册
            Member member = memberService.findByTelephone(telephone);
            if(member == null){
                // 不是会员，自动注册
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            //登录成功
            //写入Cookie，跟踪用户
            Cookie cookie = new Cookie("login_member_telephone",telephone);
            cookie.setPath("/");//路径
            cookie.setMaxAge(60*60*24*30);//有效期30天
            resp.addCookie(cookie);

            //保存会员信息到Redis中
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone,60*30,json);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }else{
            // 验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
}
