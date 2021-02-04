package com.ybzn.service;

import com.ybzn.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.pojo.Menu;
import com.ybzn.pojo.Role;
import com.ybzn.utils.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录以后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    ResultBean login (String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取admin对象
     * @param username
     * @return
     */
    Admin getAdminByUserName (String username);


    /**
     *  根据用户id获取角色列表
     * @param integer
     * @return
     */
    List<Role> getRoles(Integer integer);
    /**
     *  获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmin (String keywords);
    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    ResultBean updateAdminRole (Integer adminId, Integer[] rids);
}
