package top.hellocode.dao;

import com.github.pagehelper.Page;
import top.hellocode.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月22日 18:21
 */
public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String,Integer> map);
    public Page<Setmeal> findPage(String queryString);
    public List<Setmeal> findAll();
    public Setmeal findById(Integer id);
    public List<Map<String,Object>> findSetmealCount();
}
