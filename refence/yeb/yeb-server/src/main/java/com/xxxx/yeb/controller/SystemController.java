package com.ybzn.yeb.controller;

import com.ybzn.yeb.pojo.Menu;
import com.ybzn.yeb.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统配置Controller
 *
 * @author zhoubin
 * @since 1.0.0
 */
@RestController
@RequestMapping ("/system")
public class SystemController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation (value = "根据用户id查询菜单")
    @GetMapping ("/menu")
    public List <Menu> getMenusByAdminId () {
        return menuService.getMenusByAdminId();
    }

}
