package com.ybzn.yeb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.yeb.mapper.MenuMapper;
import com.ybzn.yeb.mapper.MenuRoleMapper;
import com.ybzn.yeb.pojo.Admin;
import com.ybzn.yeb.pojo.Menu;
import com.ybzn.yeb.service.IMenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl <MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuRoleMapper menuRoleMapper;

    /**
     * 根据用户id查询菜单
     */
    @Override
    public List <Menu> getMenusByAdminId () {
        return menuMapper.getMenusByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * 查询菜单角色
     */
    @Override
    public List <Menu> getMenusWithRole () {
        return menuMapper.getMenusWithRole();
    }

    /**
     * @Author lhr
     * @Date 21:20 2020/7/21 0021
     * 添加权限
     */
    @Override
    public boolean updateMenuRole (Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = menuRoleMapper.insertRole(rid, mids);
        return result == mids.length;
    }


    /**
     * @Author lhr
     * @Date 11:22 2020/7/22 0022
     * 获取所有menus
     */
    @Override
    public List <Menu> getAllMenus (Integer rid) {
        return menuMapper.getAllMenus(rid);
    }

    /**
     * @Author lhr
     * @Date 11:22 2020/7/22 0022
     * 获取所有的mids
     */
    @Override
    public List <Integer> getMidsByRid (Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }
}
