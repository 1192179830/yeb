package com.ybzn.controller;


import com.ybzn.pojo.Position;
import com.ybzn.service.IPositionService;
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
 *  前端控制器
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/system/config/pos")
@Api(tags = "职位管理 Controller")
public class PositionController {

    @Autowired
    private IPositionService iPositionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List <Position> getAllPositions(){
        return iPositionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping ("/")
    public ResultBean addPositions(@RequestBody Position position){
         position.setCreate_date(LocalDateTime.now());
         return iPositionService.save(position)?ResultBean.success("添加成功！"):ResultBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public ResultBean updatePosition(@RequestBody Position position){
      return iPositionService.updateById(position)?ResultBean.success("更新成功！"):ResultBean.error("更新失败！");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public ResultBean deletePosition(@PathVariable Integer id){
        return iPositionService.removeById(id)?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public ResultBean deletePositionByIds( Integer[] id){
        return iPositionService.removeByIds(Arrays.asList(id))?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }

}
