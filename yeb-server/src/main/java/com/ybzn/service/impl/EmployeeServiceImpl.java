package com.ybzn.service.impl;

import com.ybzn.pojo.Employee;
import com.ybzn.mapper.EmployeeMapper;
import com.ybzn.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
