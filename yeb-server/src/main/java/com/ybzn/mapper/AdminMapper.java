package com.ybzn.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybzn.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface AdminMapper extends BaseMapper<Admin> {


    /**
     * 获取所有操作员
     * @param id
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmin (Integer id, String keywords);
}
