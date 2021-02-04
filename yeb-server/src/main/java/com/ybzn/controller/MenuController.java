package com.ybzn.controller;


import com.ybzn.pojo.Admin;
import com.ybzn.pojo.Menu;
import com.ybzn.service.IAdminService;
import com.ybzn.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spi.service.contexts.SecurityContext;

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
@RequestMapping("/system/cfg")
@Api(tags = "菜单管理 Controller")
public class MenuController {

    @Autowired
    private IMenuService IMenuService;

    /**
     * 根据用户名id 获取菜单列表
     * @return
     */
    @ApiOperation(value = "通过用户名ID查询列表")
    @GetMapping("/menu")
    public List <Menu> getMenusByAdminId(){
        return IMenuService.getMenusByAdminId();
    }
}
