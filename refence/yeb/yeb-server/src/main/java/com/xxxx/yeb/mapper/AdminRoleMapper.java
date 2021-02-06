package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.yeb.pojo.AdminRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface AdminRoleMapper extends BaseMapper <AdminRole> {

    /**
     * 查询角色下是否拥有用户
     *
     * @author lhr
     * @date 20:41 2020/7/23 0023
     */
    List <AdminRole> selectByRid (Integer rid);
}
