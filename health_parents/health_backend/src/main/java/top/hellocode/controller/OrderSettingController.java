package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.Result;
import top.hellocode.pojo.OrderSetting;
import top.hellocode.service.OrderSettingService;
import top.hellocode.utils.POIUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月23日 16:26
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            // 读取文件
            List<String[]> list = POIUtils.readExcel(excelFile);
            // 封装数据
            List<OrderSetting> res = new ArrayList<>();
            for (String[] strings : list) {
                String data = strings[0];
                String num = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(data),Integer.parseInt(num));
                res.add(orderSetting);
            }
            // 调用service，将数据存入数据库
            orderSettingService.add(res);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try{
            List<Map> res = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,res);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
