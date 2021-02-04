package com.ybzn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybzn.config.security.compont.JwtTokenUtil;
import com.ybzn.mapper.AdminRoleMapper;
import com.ybzn.mapper.RoleMapper;
import com.ybzn.pojo.Admin;
import com.ybzn.mapper.AdminMapper;
import com.ybzn.pojo.AdminRole;
import com.ybzn.pojo.Role;
import com.ybzn.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.AdminUtils;
import com.ybzn.utils.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl <AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    /**
     * 登录以后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public ResultBean login (String username, String password, String code, HttpServletRequest request) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(password);


        System.out.println("---"+userDetailsService.loadUserByUsername(username));
        System.out.println(passwordEncoder.matches(password, userDetails.getPassword()));
        String captChar = (String) request.getSession().getAttribute("captChar");
        if(StringUtils.isEmpty(captChar)||!captChar.equalsIgnoreCase(code)){
            return ResultBean.error("验证码输入错误，请重新输入");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            System.out.println("tt:"+userDetails.getPassword());
            return ResultBean.error("用户密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return ResultBean.error("账号已经被禁用");
        }

        //更新security登录用户对象,这样以后 获取当前用户信息这些接口就可以直接获取用户信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //生产Token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map <String, String> toMap = new HashMap <>();
        toMap.put("token", token);
        toMap.put("tokenHead", tokenHead);

        return ResultBean.success("登录成功", toMap);
    }

    @Value ("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户名，获取Admin对象
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName (String username) {
        //未作健壮性判断
        return adminMapper.selectOne(new QueryWrapper <Admin>().eq("username", username)
                .eq("enabled", true));
    }

    /**
     * 根据用户id获取角色列表
     * @param adminId
     * @return
     */
    @Override
    public List <Role> getRoles (Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @Override
    public List <Admin> getAllAdmin (String keywords) {
        return adminMapper.getAllAdmin(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    public ResultBean updateAdminRole (Integer adminId, Integer[] rids) {
        //先删除用户再添加
        adminRoleMapper.delete(new QueryWrapper <AdminRole>().eq("adminId",adminId ));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if(rids.length==result){
            return ResultBean.success("更新成功！");
        }
        return ResultBean.error("更新失败！");
    }


}
