package com.ybzn.mapper;

import com.ybzn.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybzn.utils.ResultBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     * @param parentId
     */
    List<Department> getAllDepartments (int parentId);

    /**
     * 添加部门
     * @return
     * @param department
     */
    void addDepartment (Department department);

    /**
     * 删除部门
     * @return
     * @param dep
     */
    ResultBean deleteDepartment (Department dep);
}
