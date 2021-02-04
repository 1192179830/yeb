package com.ybzn.service;

import com.ybzn.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.utils.ResultBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**更新角色菜单
     * @param rid
     * @param ids
     * @return
     */
    ResultBean updateMenuRoles (Integer rid, Integer[] ids);
}
