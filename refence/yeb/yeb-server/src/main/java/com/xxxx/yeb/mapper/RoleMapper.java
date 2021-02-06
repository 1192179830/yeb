package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.yeb.pojo.Role;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface RoleMapper extends BaseMapper <Role> {

    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    List <Role> getRoles (Integer adminId);

    /**
     * @Author lhr
     * @Date 15:49 2020/7/22 0022
     * 判断角色唯一
     */
    List <Role> selectByName (String roleName);

    /**
     * @Author lhr
     * @Date 15:48 2020/7/22 0022
     * 判断角色唯一
     */
    List <Role> selectByNameZh (String roleNameZh);

    /**
     * 删除角色
     *
     * @author lhr
     * @date 20:44 2020/7/23 0023
     */
    Boolean deleteRoleByRid (Integer rid);
}
