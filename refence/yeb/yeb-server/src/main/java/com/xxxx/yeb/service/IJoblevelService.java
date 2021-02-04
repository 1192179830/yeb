package com.xxxx.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.pojo.Joblevel;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IJoblevelService extends IService <Joblevel> {

    /**
     * @description: 查询职称列表
     * @param:
     * @return: {@link List< Joblevel>}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 9:11
     */
    List <Joblevel> getJobLevelList ();

    /**
     * @description: 增加职称
     * @param: joblevel
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 21:40
     */
    void addJobLevel (Joblevel joblevel);

    /**
     * @description: 修改职称
     * @param: joblevel
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 21:40
     */
    void updateJobLevel (Joblevel joblevel);

    /**
     * @description: 批量删除职称
     * @param: ids
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 22:15
     */
    void deleteJobLevel (String ids);
}
