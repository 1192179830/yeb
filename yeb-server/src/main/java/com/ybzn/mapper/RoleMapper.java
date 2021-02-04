package com.ybzn.mapper;

import com.ybzn.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id获取角色列表
     * @param adminId
     * @return
     */
     List <Role> getRoles (Integer adminId) ;
}
