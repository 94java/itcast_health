package top.hellocode.service;

import top.hellocode.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月23日 16:31
 */
public interface OrderSettingService {
    public void add(List<OrderSetting> orderSettings);
    public List<Map> getOrderSettingByMonth(String date);
    public void editNumberByDate(OrderSetting orderSetting);
}
