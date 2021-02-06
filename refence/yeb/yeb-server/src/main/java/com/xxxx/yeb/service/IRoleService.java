package com.ybzn.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.pojo.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IRoleService extends IService <Role> {

    /**
     * 权限的添加
     *
     * @param name   角色英文名
     * @param nameZh 角色中文名
     * @return com.xxxx.yeb.pojo.RespBean
     * @author lhr
     * @version V1.0.0
     * @date 22:06 2020/7/23 0023
     */
    RespBean insertRole (String name, String nameZh);

    /**
     * 获取角色列表
     *
     * @return java.util.List<com.xxxx.yeb.pojo.Role>
     * @author lhr
     * @version V1.0.0
     * @date 22:13 2020/7/23 0023
     */
    List <Role> roleList ();

    /**
     * 根据角色id删除角色
     *
     * @param rid 角色id
     * @return com.xxxx.yeb.pojo.RespBean
     * @author lhr
     * @version V1.0.0
     * @date 22:05 2020/7/23 0023
     */
    RespBean deleteRoleByRid (Integer rid);
}
