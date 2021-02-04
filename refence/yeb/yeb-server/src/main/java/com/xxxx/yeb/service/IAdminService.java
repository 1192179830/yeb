package com.xxxx.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yeb.enums.EnabledChangeEnum;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.AdminRole;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IAdminService extends IService <Admin> {

    /**
     * 登录成功返回token
     *
     * @param username
     * @param password
     * @return
     */
    RespBean login (String username, String password, String captcha, HttpServletRequest request);


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName (String username);

    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    List <Role> getRoles (Integer adminId);

    /**
     * @description: 查询操作员列表
     * @param: keywords
     * @return: {@link List< Admin>}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 14:05
     */
    List <Admin> selectAdminList (String keywords);

    /**
     * @description: 查询所有角色列表
     * @param:
     * @return: {@link List< Role>}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 17:35
     */
    List <Role> selectAllRoleList ();

    /**
     * @description: 修改用户角色信息
     * @param: adminId
     * @param: rids
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 18:00
     */
    void updateAdminRole (Integer adminId, String rids);

    /**
     * @param admin
     * @description: 禁用用户
     * @param: admin
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 19:37
     */
    EnabledChangeEnum enabledChange (Map <String, Object> admin);

    /**
     * @description: 通过id删除用户
     * @param: id
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/18 21:03
     */
    void deleteAdminById (Integer id);

    /**
     * @Author lhr
     * @Date 21:39 2020/7/22 0022
     * 查询角色下是否有用户
     */
    List <AdminRole> selectByrid (Integer rid);


    /**
     * @description: 修改用户密码
     * @param: ruleForm
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/20 20:57
     */
    void updatePassword (Map <String, Object> ruleForm);

    /**
     * @param admin
     * @description: 修改用户信息
     * @param: admin
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/20 21:59
     */
    void updateAdminInfo (String admin);
}
