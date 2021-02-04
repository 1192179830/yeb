package com.ybzn.service;

import com.ybzn.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.utils.ResultBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments ();
    /**
     * 添加部门
     * @param department
     * @return
     */
    ResultBean addDepartment (Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    ResultBean deleteDepartment (Integer id);
}
