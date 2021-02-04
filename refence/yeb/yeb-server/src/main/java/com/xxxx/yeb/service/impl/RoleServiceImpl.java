package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.yeb.mapper.AdminRoleMapper;
import com.xxxx.yeb.mapper.MenuRoleMapper;
import com.xxxx.yeb.mapper.RoleMapper;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl <RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private AdminRoleMapper adminRoleMapper;
    @Resource
    private MenuRoleMapper menuRoleMapper;


    /**
     * 角色的添加
     *
     * @param roleName
     * @param roleNameZh
     * @return
     */
    @Override
    public RespBean insertRole (String roleName, String roleNameZh) {
        //判断roleName,roleNameZh非空
        if (null == roleName || roleName.isEmpty() || null == roleNameZh || roleNameZh.isEmpty()) {
            return RespBean.error("角色名不能为空");
        }
        //角色唯一性判断
        if (roleMapper.selectByName(roleName).size() != 0) {
            return RespBean.error("角色英文名已存在，请确认后重新添加");
        }
        if (roleMapper.selectByNameZh(roleNameZh) != null) {
            return RespBean.error("角色中文名已存在，请确认后重新添加");
        }
        Role role = new Role();
        role.setName("ROLE_" + roleName.trim());
        role.setNameZh(roleNameZh.trim());
        // 健壮性判断
        if (roleMapper.insert(role) < 1) {
            return RespBean.error("角色添加失败，请刷新后重试");
        }
        return RespBean.success("角色添加成功");
    }

    /**
     * @Author lhr
     * @Date 11:12 2020/7/19 0019
     * 查询角色组，前台显示
     */
    @Override
    public List <Role> roleList () {
        return roleMapper.selectList(new QueryWrapper <>());
    }


    /**
     * @param rid
     * @Author lhr
     * @Return com.xxxx.yeb.pojo.RespBean
     * @Version V1.0.0
     * @Date 9:30 2020/7/20 0020
     * 根据角色id删除角色
     */
    @Override
    public RespBean deleteRoleByRid (Integer rid) {
        // 查询角色下有无用户;返回对象集合
        if (adminRoleMapper.selectByRid(rid).size() > 0) {
            return RespBean.error("角色下存在用户无法删除，请到操作员管理取消角色下所有用户再删除");
        }
        // 查询角色下是否有权限，返回集合；集合为空代表没有权限，角色直接删除；有权限，先删权限再删角色
        if (menuRoleMapper.selectByRid(rid).size() == 0) {
            // 没有权限直接删除角色
            if (roleMapper.deleteRoleByRid(rid)) {
                return RespBean.success("角色删除成功");
            }
            return RespBean.error("角色删除失败，请刷新后重试!!!!!!!!!!!");
        } else {
            // 判断删除权限是否成功，返回Boolean类型，为真则删除成功；然后删除角色
            if (menuRoleMapper.deleteByRid(rid)) {
                // 判断角色是否删除成功，返回Boolean值，
                if (roleMapper.deleteRoleByRid(rid)) {
                    return RespBean.success("角色删除成功");
                }
                return RespBean.error("角色删除失败，请刷新后重试!!!!!!!!!!!");
            }
            return RespBean.error("角色删除失败，请刷新后重试!!!!!!!!!!!");
        }

    }


}
