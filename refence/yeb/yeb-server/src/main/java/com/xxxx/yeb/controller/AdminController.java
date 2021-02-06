package com.ybzn.yeb.controller;


import com.ybzn.yeb.enums.EnabledChangeEnum;
import com.ybzn.yeb.pojo.Admin;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.pojo.Role;
import com.ybzn.yeb.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@RestController
public class AdminController {

    @Resource
    private IAdminService adminService;


    @GetMapping ("/system/admin")
    @ApiOperation (value = "查询操作员列表")
    public List <Admin> selectAdminList (String keywords) {
        // 条件查询列表
        List <Admin> adminList = adminService.selectAdminList(keywords);

        return adminList;
    }

    @GetMapping ("/system/admin/roles")
    @ApiOperation (value = "查询所有角色列表")
    public List <Role> selectAllRoleList () {

        return adminService.selectAllRoleList();
    }

    @PutMapping ("/system/admin/role")
    @ApiOperation (value = "修改用户角色")
    public RespBean updateAdminRole (Integer adminId, String rids) {
        adminService.updateAdminRole(adminId, rids);
        return RespBean.success("用户角色信息修改成功！");
    }

    @PutMapping ("/system/admin/")
    @ApiOperation (value = "禁用或启用用户")
    public RespBean enabledChange (@RequestBody Map <String, Object> admin) {

        EnabledChangeEnum enabledChange = adminService.enabledChange(admin);
        switch (enabledChange) {
            case EABLED:
                return RespBean.error("用户已经被禁用！");
            case DISABLE:
                return RespBean.success("用户禁用成功");
            case ENABLE:
                return RespBean.success("用户启用成功！");
            case ENABLED:
                return RespBean.error("用户已经被启用");
            default:
                return RespBean.error("无效操作！");
        }
    }

    @DeleteMapping ("/system/admin/{id}")
    @ApiOperation (value = "删除用户")
    public RespBean deleteAdmin (@PathVariable Integer id) {
        adminService.deleteAdminById(id);
        return RespBean.success("用户删除成功！");
    }

    @PutMapping ("/admin/pass")
    @ApiOperation (value = "修改用户密码")
    public RespBean updatePassword (@RequestBody Map <String, Object> ruleForm) {
        adminService.updatePassword(ruleForm);
        return RespBean.success("密码修改成功！");
    }

}
