package top.hellocode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.hellocode.dao.OrderSettingDao;
import top.hellocode.pojo.OrderSetting;
import top.hellocode.service.OrderSettingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月23日 16:31
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> list){
        if(list != null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                if(orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate()) > 0){
                    // 执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    // 执行新增操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String begin = date + "-1";     //  2022-8-1
        String end = date + "-31";      // 2022-8-31
        Map<String,String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> res = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map m = new HashMap();
            m.put("date",orderSetting.getOrderDate().getDate());        // 获取日
            m.put("number",orderSetting.getNumber());       // 获取可预约数
            m.put("reservations",orderSetting.getReservations());   // 获取已预约数
            res.add(m);
        }
        return res;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            // 执行更新操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            // 执行新增
            orderSettingDao.add(orderSetting);
        }
    }
}
