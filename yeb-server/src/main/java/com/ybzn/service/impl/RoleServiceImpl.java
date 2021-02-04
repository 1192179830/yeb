package com.ybzn.service.impl;

import com.ybzn.pojo.Role;
import com.ybzn.mapper.RoleMapper;
import com.ybzn.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.ResultBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
