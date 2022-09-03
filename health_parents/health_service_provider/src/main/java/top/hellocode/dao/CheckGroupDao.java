package top.hellocode.dao;

import com.github.pagehelper.Page;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月20日 20:40
 */
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> queryPage(String queryString);
    public Integer[] findCheckItemIds(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteAssociation(Integer id);
    public void delete(Integer id);
    public List<CheckGroup> findAll();
}
