package top.hellocode.service;

import com.github.pagehelper.Page;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.pojo.CheckGroup;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月20日 20:37
 */
public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);
    public PageResult queryPage(QueryPageBean queryPageBean);
    public Integer[] findCheckItemIds(Integer id);
    public void edit(Integer[] checkitemIds,CheckGroup checkGroup);
    public void delete(Integer id);
    public List<CheckGroup> findAll();
}
