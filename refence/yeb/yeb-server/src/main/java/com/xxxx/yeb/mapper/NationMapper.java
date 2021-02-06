package com.ybzn.yeb.mapper;

import com.ybzn.yeb.pojo.Nation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface NationMapper extends BaseMapper <Nation> {
    int selectIdByName (String name);

    Nation selectNationById (Integer id);

}
