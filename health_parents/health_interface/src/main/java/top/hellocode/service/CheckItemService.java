package top.hellocode.service;

import org.springframework.web.bind.annotation.RequestBody;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.pojo.CheckItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月19日 16:34
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult findByCondition(QueryPageBean queryPageBean);
    public void deleteById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
}
