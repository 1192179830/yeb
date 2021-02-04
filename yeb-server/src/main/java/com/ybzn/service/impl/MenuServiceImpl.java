package com.ybzn.service.impl;

import com.ybzn.pojo.Admin;
import com.ybzn.pojo.Menu;
import com.ybzn.mapper.MenuMapper;
import com.ybzn.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.AdminUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据用户ID 查询菜单列表
     * @return
     */
    @Override
    public List <Menu> getMenusByAdminId () {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations<String,Object> opt = redisTemplate.opsForValue();
        // 从redis获取菜单数据，若为空，则到数据库获取
        List<Menu> list =((List<Menu>) opt.get("menu_" + adminId));

         if(CollectionUtils.isEmpty(list)){
            list= menuMapper.getMenusByAdminId(adminId);
            //将数据设置到redis中
            opt.set("menu_"+adminId, list);
         }
        return menuMapper.getMenusByAdminId(adminId);
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List <Menu> getMenuWithRole () {

        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List <Menu> getAllMenus () {

        return menuMapper.getAllMenus();
    }
}
