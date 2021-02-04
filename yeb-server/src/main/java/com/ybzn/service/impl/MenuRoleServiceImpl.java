package com.ybzn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybzn.mapper.MenuMapper;
import com.ybzn.pojo.MenuRole;
import com.ybzn.mapper.MenuRoleMapper;
import com.ybzn.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     * @param rid
     * @param ids
     * @return
     */
    @Override
    public ResultBean updateMenuRoles (Integer rid, Integer[] ids) {
        menuRoleMapper.delete(new QueryWrapper <MenuRole>().eq("rid", rid));
        if (null ==ids ||0==ids.length){
            return ResultBean.success("更新成功");
        }
        Integer column= menuRoleMapper.insertRecord(rid ,ids);
        if(column ==ids.length){
            return ResultBean.success("更新成功");
        }
        return ResultBean.success("更新失败");
    }
}
