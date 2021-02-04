package com.ybzn.service;

import com.ybzn.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface IMenuService extends IService<Menu> {


    /**
     * 根据用户名id 获取菜单列表
     * @param
     * @return
     */
    List<Menu> getMenusByAdminId ();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuWithRole();

    /**
     * 查询所有菜单
     * @return
     */
    List <Menu> getAllMenus ();
}
