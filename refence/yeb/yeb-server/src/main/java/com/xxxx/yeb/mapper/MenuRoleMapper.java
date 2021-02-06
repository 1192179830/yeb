package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.yeb.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface MenuRoleMapper extends BaseMapper <MenuRole> {

    /**
     * @Author lhr
     * @Date 11:33 2020/7/21 0021
     * 根据角色id查询该角色是否别引用
     */
    List <MenuRole> selectByRid (Integer rid);

    /**
     * 添加权限
     *
     * @author lhr
     * @date 20:43 2020/7/23 0023
     */
    Integer insertRole (@Param ("rid") Integer rid, @Param ("mids") Integer[] mids);

    /**
     * 删除角色
     *
     * @author lhr
     * @date 20:43 2020/7/23 0023
     */
    Boolean deleteByRid (Integer rid);


}
