package com.ybzn.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.yeb.pojo.Position;
import com.ybzn.yeb.pojo.RespBean;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IPositionService extends IService <Position> {

    /**
     * 查询职位所有信息
     *
     * @return
     */
    RespBean selectPosition ();

    /**
     * 删除单条数据
     *
     * @param id
     * @return
     */
    RespBean deletePosition (Integer id);

    /**
     * 删除多条数据
     *
     * @param ids
     * @return
     */
    RespBean deleteAllPosition (String ids);

    /**
     * 添加职位数据
     *
     * @param pos
     * @return
     */
    Boolean addPosition (Position pos);

    /**
     * 修改职位数据
     *
     * @param updatePos
     * @return
     */
    Boolean updatePosition (Position updatePos);
}
