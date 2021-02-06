package com.ybzn.yeb.service;

import com.ybzn.yeb.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybzn.yeb.pojo.RespBean;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
public interface IDepartmentService extends IService <Department> {

    List <Department> queryAllModules ();


    RespBean addDepartment (Department department);

    void deleteDepartment (Integer id);
}
