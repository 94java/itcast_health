package top.hellocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import top.hellocode.constant.MessageConstant;
import top.hellocode.constant.RedisConstant;
import top.hellocode.constant.RedisMessageConstant;
import top.hellocode.entity.Result;
import top.hellocode.utils.SMSUtils;
import top.hellocode.utils.ValidateCodeUtils;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月27日 16:23
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        // 生产验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        // 发送短信
        try{
            SMSUtils.sendShortMessage(telephone,code.toString());
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        // 将验证码存入redis（5分钟）
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,5 * 60,code.toString());
        // 发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        // 生产验证码
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        // 发送短信
        try{
            SMSUtils.sendShortMessage(telephone,code.toString());
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        // 将验证码存入redis（5分钟）
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN,5 * 60,code.toString());
        // 发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
