package com.ybzn.mapper;

import com.ybzn.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.utils.ResultBean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {


    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addAdminRole (Integer adminId, Integer[] rids);
}
