package top.hellocode.dao;

import com.github.pagehelper.Page;
import top.hellocode.pojo.CheckItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月19日 16:37
 */
public interface CheckItemDao {
    // 添加检查项
    public void add(CheckItem checkItem);
    public Page<CheckItem> findByCondition(String queryString);
    public void deleteById(Integer id);
    public long findCheckGroupByCheckItemId(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();
}
