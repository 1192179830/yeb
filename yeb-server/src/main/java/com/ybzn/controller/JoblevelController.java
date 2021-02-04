package com.ybzn.controller;


import com.ybzn.pojo.Joblevel;
import com.ybzn.pojo.Position;
import com.ybzn.service.IJoblevelService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器 职称管理
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/system/basic/joblevel")
@Api (tags = "职称管理 Controller")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping
    public List<Joblevel> getAllJoblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping
    public ResultBean addAllJoblevels(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        return joblevelService.save(joblevel)? ResultBean.success("添加成功！"):ResultBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public ResultBean updatePosition(@RequestBody Joblevel joblevel){
        return joblevelService.updateById(joblevel)?ResultBean.success("更新成功！"):ResultBean.error("更新失败！");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public ResultBean deletePosition(@PathVariable Integer id){
        return joblevelService.removeById(id)?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public ResultBean deletePositionByIds( Integer[] id){
        return joblevelService.removeByIds(Arrays.asList(id))?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }


}
