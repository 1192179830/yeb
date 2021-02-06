package com.ybzn.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.yeb.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IMenuService extends IService <Menu> {

    /**
     * 根据用户id查询菜单
     *
     * @return
     */
    List <Menu> getMenusByAdminId ();


    /**
     * 查询菜单角色
     *
     * @return
     */
    List <Menu> getMenusWithRole ();

    /**
     * @Author lhr
     * @Date 21:19 2020/7/21 0021
     * 添加权限
     */
    boolean updateMenuRole (Integer rid, Integer[] mids);

    /**
     * @Author lhr
     * @Date 11:16 2020/7/22 0022
     * 获取menus
     */
    List <Menu> getAllMenus (Integer rid);

    /**
     * @Author lhr
     * @Date 11:22 2020/7/22 0022
     * 获取所有mids
     */
    List <Integer> getMidsByRid (Integer rid);
}
