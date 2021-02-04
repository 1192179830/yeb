package com.ybzn.service.impl;

import com.ybzn.pojo.Department;
import com.ybzn.mapper.DepartmentMapper;
import com.ybzn.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List <Department> getAllDepartments () {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public ResultBean addDepartment (Department department) {
        departmentMapper.addDepartment(department);
        if(1==department.getResult()){//若添加成功，则返回的department中的结果字段为1,
            return ResultBean.success("添加成功！",department);
        }else{
            return ResultBean.error("添加失败！");
        }
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public ResultBean deleteDepartment (Integer id) {
        Department dep =new Department();
        dep.setId(id);
        dep.setEnabled(true);
        departmentMapper.deleteDepartment(dep);//执行以后，会返回一个pojo类
        if(dep.getResult()==-2){
            return ResultBean.error("该部门下面还有子部门，删除失败！");
        }else if(dep.getResult()==-1){
            return ResultBean.error("该部门下面还有员工，删除失败！");
        }
        if(dep.getResult()==1){
            return ResultBean.success("删除成功!");
        }
        return  ResultBean.error("删除失败！");
    }
}
