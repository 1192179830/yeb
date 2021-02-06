package com.ybzn.controller;

import com.ybzn.pojo.Admin;
import com.ybzn.pojo.AdminLoginParam;
import com.ybzn.service.IAdminService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 用户登录实体类
 *
 * @author Hugo
 * @time 2021/1/20
 */
@Api(tags = "登录管理 Controller")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public ResultBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request)
    {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal)
    {
        if(null == principal){
            return null;
        }
        String username =principal.getName();
        Admin admin =adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResultBean logout(){
        return ResultBean.success("注销成功!");//后端主要返回操作成功200状态码，前端删除Token
    }


}
