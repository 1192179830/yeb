package com.ybzn.mapper;

import com.ybzn.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户名id 获取菜单列表
     * @param
     * @param Id
     * @return
     */
    List <Menu> getMenusByAdminId (Integer Id);

    /**
     * 根据用户名id 获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole ();

    /**查询所有菜单
     *
     * @return
     * @param id
     */
    List<Menu> getAllMenus (Integer id);
}
