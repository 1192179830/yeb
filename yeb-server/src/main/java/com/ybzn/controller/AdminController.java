package com.ybzn.controller;


import com.ybzn.pojo.Admin;
import com.ybzn.pojo.Role;
import com.ybzn.service.IAdminService;
import com.ybzn.service.IRoleService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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
@RequestMapping("/system/admin")
@Api(tags = "操作员管理 Controller")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List <Admin> getAllAdmin(String keywords){
        return adminService.getAllAdmin(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public ResultBean updateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return ResultBean.success("更新成功!");
        }
        return ResultBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public ResultBean deleteAdmin(@PathVariable Integer id){
        if(adminService.removeById(id)){
            return ResultBean.success("删除成功!");
        }
        return ResultBean.error("删除失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/roles")
    public ResultBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }
}
