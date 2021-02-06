package com.ybzn.yeb.controller;


import com.ybzn.yeb.pojo.Position;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@RestController
@RequestMapping ("/system/basic/")
public class PositionController {
    @Autowired
    private IPositionService positionService;


    /**
     * 查询职位所有信息
     *
     * @return
     */
    @GetMapping ("/pos/")
    @ResponseBody
    public List <Position> getPosition () {
        //获取职位对象
        Position position = new Position();
        //调用positionService的查询职位所有信息的方法，返回一个RespBean
        RespBean respBean = positionService.selectPosition();
        //获取RespBean中Obj强转为List<Position>
        List <Position> positionList = (List <Position>) respBean.getObj();
        //返回查询出来的集合
        return positionList;
    }

    /**
     * 删除单条数据
     *
     * @param id
     * @return
     */
    @DeleteMapping ("/pos/{id}")
    @ResponseBody
    @ApiOperation (value = "根据传过来的ID进行单条数据删除")
    public RespBean deletePosition (@PathVariable ("id") Integer id) {
        //调用删除方法
        RespBean respBean = positionService.deletePosition(id);
        List <Position> position = getPosition();
        return respBean;
    }

    /**
     * 删除多条数据
     *
     * @param ids
     * @return
     */
    @DeleteMapping ("/pos/")
    @ResponseBody
    @ApiOperation (value = "根据传过来的ID数组进行多条数据删除")
    public RespBean deletePosition (String ids) {
        //调用删除方法
        RespBean respBean = positionService.deleteAllPosition(ids);
        List <Position> position = getPosition();
        return respBean;
    }

    /**
     * 添加职位数据
     *
     * @param pos
     * @return
     */
    @PostMapping ("/pos/")
    @ApiOperation (value = "根据传过来的对象添加职位")
    public RespBean addPosition (@RequestBody Position pos) {
        // 调用添加方法获取返回值
        Boolean result = positionService.addPosition(pos);
        //返回值为false--添加失败
        if (result == false) {
            return RespBean.error("该职位已存在");
        }
        return RespBean.success("职位添加成功");
    }

    /**
     * 修改职位数据
     *
     * @param updatePos
     * @return
     */
    @PutMapping ("/pos/")
    @ApiOperation (value = "根据传过来的对象修改职位")
    public RespBean updatePosition (@RequestBody Position updatePos) {
        //调用修改方法，获取返回值
        Boolean result = positionService.updatePosition(updatePos);
        //返回值为false--添加失败
        if (result == false) {
            return RespBean.error("修改失败！");
        }
        return RespBean.success("修改成功！");
    }
}
