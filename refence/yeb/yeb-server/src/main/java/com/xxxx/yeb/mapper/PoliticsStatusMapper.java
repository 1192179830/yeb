package com.ybzn.yeb.mapper;

import com.ybzn.yeb.pojo.PoliticsStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface PoliticsStatusMapper extends BaseMapper <PoliticsStatus> {
    int selectIdByName (String name);

    PoliticsStatus selectPoliticsStatusById (Integer id);

}
