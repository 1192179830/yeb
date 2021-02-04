package com.xxxx.yeb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.yeb.pojo.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface DepartmentMapper extends BaseMapper <Department> {

    List <Department> queryAllModules ();

    int addOrUpdateDepartment (String name);

    String queryDepartMent (String name);

    Department selectDeparymentById (Integer id);

    int selectIdByName (String name);
}
