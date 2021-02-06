package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.yeb.pojo.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface MenuMapper extends BaseMapper <Menu> {

    /**
     * 根据用户id查询菜单
     *
     * @param id
     * @return
     */
    List <Menu> getMenusByAdminId (Integer id);

    /**
     * 查询菜单角色
     *
     * @return
     */
    List <Menu> getMenusWithRole ();

    /**
     * @return
     */
    List <Map <String, Object>> getMenusById (Integer rid);

    List <Menu> getAllMenus (Integer rid);

    /**
     * @Author lhr
     * @Date 11:23 2020/7/22 0022
     * 获取所有mids
     */
    List <Integer> getMidsByRid (Integer rid);
}
