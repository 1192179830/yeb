package com.ybzn.service;

import com.ybzn.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.utils.ResultBean;
import com.ybzn.utils.ResultPageBean;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 分页查询 员工信息
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    ResultPageBean getEmployeeByPage (Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return
     */
    ResultBean maxWorkId ();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    ResultBean addEmp (Employee employee);
}
