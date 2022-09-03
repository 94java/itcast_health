package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import top.hellocode.constant.MessageConstant;
import top.hellocode.constant.RedisMessageConstant;
import top.hellocode.entity.Result;
import top.hellocode.pojo.Order;
import top.hellocode.service.OrderService;

import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月27日 18:55
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        // 校验验证码
        String code4Redis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String code = (String) map.get("validateCode");
        if(code4Redis != null && code != null && code.equals(code4Redis)){
            // 校验通过
            map.put("orderType",Order.ORDERTYPE_WEIXIN);
            try{
                Result result = orderService.order(map);
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,MessageConstant.ORDERSETTING_FAIL);
            }
        }else {
            // 校验不通过
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        // 预约成功，发送短信通知
        //.......
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
