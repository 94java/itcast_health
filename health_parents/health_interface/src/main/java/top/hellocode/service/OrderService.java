package top.hellocode.service;

import top.hellocode.entity.Result;

import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月27日 19:28
 */
public interface OrderService {
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;
}
