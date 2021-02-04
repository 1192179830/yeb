package com.ybzn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybzn.pojo.*;
import com.ybzn.service.IMenuRoleService;
import com.ybzn.service.IMenuService;
import com.ybzn.service.IRoleService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组
 * @author Hugo
 * @time 2021/1/25
 */
@RestController
@Api(tags = "权限 Controller")
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService iMenuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List <Role> getAllRoles(){
        return iRoleService.list();
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public List<Menu> getAllMenus(){
        List<Menu> list =menuService.getAllMenus();
        return menuService.getAllMenus();
    }

    @ApiOperation (value = "根据角色id查询菜单")
    @GetMapping ("/mid/{rid}")
    public List <Integer> getMidByRid(@PathVariable Integer rid)
    {
        return iMenuRoleService.list(new QueryWrapper <MenuRole>().eq("rid", rid)).stream().map(MenuRole::getId).collect(Collectors.toList());
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public ResultBean addRoles(@RequestBody Role role){
        if(!role.getName().startsWith("ROLE")){
            role.setName("ROLE_"+role.getName());
        }
        return iRoleService.save(role)? ResultBean.success("添加成功！"):ResultBean.error("添加失败！");
    }


    @ApiOperation(value = "删除角色菜单")
    @DeleteMapping("/role/{rid}")
    public ResultBean deletePosition(@PathVariable Integer id){
        return iRoleService.removeById(id)?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除权限信息")
    @DeleteMapping("/")
    public ResultBean deletePositionByIds( Integer[] id){
        return iRoleService.removeByIds(Arrays.asList(id))?ResultBean.success("删除成功！"):ResultBean.error("删除失败！");
    }



}

