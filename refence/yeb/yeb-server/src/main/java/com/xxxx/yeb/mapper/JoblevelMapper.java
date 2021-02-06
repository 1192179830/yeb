package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.yeb.pojo.Joblevel;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface JoblevelMapper extends BaseMapper <Joblevel> {

    int selectIdByName (String name);

    Joblevel selectJoblevelById (Integer id);
}
