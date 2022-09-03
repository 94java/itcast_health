package top.hellocode.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;
import top.hellocode.constant.RedisConstant;
import top.hellocode.utils.QiniuUtils;

import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月23日 13:48
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set = jedisPool.getResource()
                .sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            for (String fileName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(fileName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
                System.out.println("已经清理垃圾图片:" + fileName);
            }
        }
    }
}
