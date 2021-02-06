package com.ybzn.controller;


import com.ybzn.service.IMenuRoleService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/menu-role")
@Api (tags = "权限 Controller")
public class MenuRoleController {

    @Autowired
    private IMenuRoleService iMenuRoleService;

    @ApiOperation("更新角色菜单")
    @PutMapping ("/")
    public ResultBean updateMenuRoles(Integer rid, Integer[] ids){
        return iMenuRoleService.updateMenuRoles(rid,ids);
    }
}
