package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.entity.Result;
import top.hellocode.pojo.CheckGroup;
import top.hellocode.service.CheckGroupService;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月20日 20:34
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try{
            checkGroupService.add(checkGroup,checkitemIds);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.queryPage(queryPageBean);
    }

    @RequestMapping("/findCheckItemIds")
    public Result findCheckItemIds(Integer id){
        try {
            Integer[] items = checkGroupService.findCheckItemIds(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,items);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup){
        try{
            checkGroupService.edit(checkitemIds,checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
