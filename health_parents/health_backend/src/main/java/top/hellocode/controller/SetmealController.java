package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.hellocode.constant.MessageConstant;
import top.hellocode.constant.RedisConstant;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.entity.Result;
import top.hellocode.pojo.Setmeal;
import top.hellocode.service.SetmealService;
import top.hellocode.utils.QiniuUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月22日 16:36
 */
@RequestMapping("/setmeal")
@RestController
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        String fileName = UUID.randomUUID().toString().replace("-", "");
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            // 只要是上传图片，将对应名称都存入redis，判断是否是垃圾图片
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(Integer[] checkgroupIds, @RequestBody Setmeal setmeal){
        try{
            setmealService.add(checkgroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }
}
