package top.hellocode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.hellocode.dao.CheckGroupDao;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.pojo.CheckGroup;
import top.hellocode.service.CheckGroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月20日 20:38
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        Integer id = checkGroup.getId();
        setCheckGroupAndCheckItem(checkitemIds, id);
    }

    private void setCheckGroupAndCheckItem(Integer[] checkitemIds, Integer id) {
        for (Integer checkitemId : checkitemIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("checkgroup_id", id);
            map.put("checkitem_id",checkitemId);
            checkGroupDao.setCheckGroupAndCheckItem(map);
        }
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.queryPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer[] findCheckItemIds(Integer id) {
        return checkGroupDao.findCheckItemIds(id);
    }

    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        // 基本信息编辑
        checkGroupDao.edit(checkGroup);
        // 检查项信息：先删除，后新增
        checkGroupDao.deleteAssociation(checkGroup.getId());
        setCheckGroupAndCheckItem(checkitemIds, checkGroup.getId());
    }

    @Override
    public void delete(Integer id) {
        // 删除关联的检查项信息
        checkGroupDao.deleteAssociation(id);
        // 删除检查组信息
        checkGroupDao.delete(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
