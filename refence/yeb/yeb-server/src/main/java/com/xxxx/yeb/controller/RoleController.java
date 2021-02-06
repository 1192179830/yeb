package com.ybzn.yeb.controller;


import com.ybzn.yeb.pojo.Menu;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.pojo.Role;
import com.ybzn.yeb.service.IMenuRoleService;
import com.ybzn.yeb.service.IMenuService;
import com.ybzn.yeb.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping ("/system/basic/permiss")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuRoleService iMenuRoleService;
    @Resource
    private IMenuService menuService;


    /**
     * @Author lhr
     * @Date 22:13 2020/7/18 0018
     * 添加角色
     */
    @PostMapping ("/role")
    @ApiOperation (value = "添加角色")
    public RespBean addRole (@RequestBody Role role) {
        //将添加结果存入公共返回对象
        return roleService.insertRole(role.getName(), role.getNameZh());
    }


    /**
     * @param
     * @Author lhr
     * @Return java.util.List<com.xxxx.yeb.pojo.Role>
     * @Version V1.0.0
     * @Date 11:05 2020/7/19 0019
     * 查询角色列表
     */
    @ApiOperation (value = "显示角色列表")
    @GetMapping
    public List <Role> roleList () {
        return roleService.roleList();
    }


    /**
     * @Author lhr
     * @Date 15:05 2020/7/19 0019
     * 根据角色id查询拥有的权限
     */
    @ApiOperation (value = "角色权限回显")
    @GetMapping ("/mid/{rid}")
    @ResponseBody
    public List <Integer> getMidsByRids (@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    /**
     * @param rid
     * @Author lhr
     * @Return void
     * @Version V1.0.0
     * @Date 21:36 2020/7/19 0019
     * 根据角色id删除角色记录
     */
    @ApiOperation (value = "删除角色")
    @DeleteMapping ("/role/{rid}")
    @ResponseBody
    public RespBean deleteRole (@PathVariable Integer rid) {
        // 进行参数的非空校验
        if (null == rid || rid == 0) {
            return RespBean.error("角色不存在请刷新后重试");
        }
        return roleService.deleteRoleByRid(rid);
    }

    /**
     * @param rid  角色id
     * @param mids 关联表中mids
     * @return com.xxxx.yeb.pojo.RespBean
     * @author lhr
     * @version V1.0.0
     * @date 15:28 2020/7/22 0022
     */
    @ApiOperation (value = "角色授权")
    @PutMapping ("/")
    public RespBean updateMenuRole (Integer rid, Integer[] mids) {
        // 更新成功
        if (menuService.updateMenuRole(rid, mids)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * @Author lhr
     * @Date 11:36 2020/7/19 0019
     * 所有角色权限显示
     */
    @ApiOperation (value = "查询所有角色")
    @GetMapping ("/menus")
    @ResponseBody
    public List <Menu> menuList (Integer rid) {
        return menuService.getAllMenus(rid);
    }

}
