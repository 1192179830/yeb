package com.ybzn.yeb.mapper;

import com.ybzn.yeb.pojo.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Repository
public interface PositionMapper extends BaseMapper <Position> {

    /**
     * 因为name是唯一值，如果用insert插入会出现冲突
     * 所以使用replace into进行插入操作
     * 但是慎用
     *
     * @param name
     * @return
     */
    @Insert ("REPLACE INTO t_position(NAME) VALUES (#{pos.getName})")
    int addPosition (String name);

    int selectIdByName (String name);

    Position selectPositionById (Integer id);
}
