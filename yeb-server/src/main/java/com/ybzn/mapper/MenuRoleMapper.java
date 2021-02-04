package com.ybzn.mapper;

import com.ybzn.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.utils.ResultBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    /***
     * 更新角色菜单
     * @return
     */
    ResultBean updateMenuRoles ();

    /**
     * 插入角色
     * @param rid
     * @param ids
     * @return
     */
    Integer insertRecord (@Param ("rid") Integer rid, @Param ("ids") Integer[] ids);
}
