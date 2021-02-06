package com.ybzn.yeb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.yeb.mapper.MenuRoleMapper;
import com.ybzn.yeb.pojo.MenuRole;
import com.ybzn.yeb.service.IMenuRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl <MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Resource
    private MenuRoleMapper menuRoleMapper;


}
