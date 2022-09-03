package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.PageResult;
import top.hellocode.entity.QueryPageBean;
import top.hellocode.entity.Result;
import top.hellocode.pojo.CheckItem;
import top.hellocode.service.CheckItemService;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月19日 16:32
 */

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    public Result add(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")//权限校验
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult result = checkItemService.findByCondition(queryPageBean);
        return result;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckItem> checkItems = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
