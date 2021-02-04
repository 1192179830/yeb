package com.ybzn.service.impl;

import com.ybzn.pojo.User;
import com.ybzn.mapper.UserMapper;
import com.ybzn.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
