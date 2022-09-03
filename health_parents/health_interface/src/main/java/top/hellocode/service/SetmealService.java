package top.hellocode.service;

import com.github.pagehelper.Page;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.pojo.Setmeal;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月22日 18:19
 */
public interface SetmealService {
    public void add(Integer[] checkgroupIds, Setmeal setmeal);
    public PageResult findPage(QueryPageBean queryPageBean);
    public List<Setmeal> findAll();
    public Setmeal findById(Integer id);
    public List<Map<String,Object>> findSetmealCount();
}
