package com.ybzn.controller;


import com.ybzn.pojo.Department;
import com.ybzn.service.IDepartmentService;
import com.ybzn.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/system/basic/department")
@Api(tags = "部门管理 Controller")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List <Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public ResultBean addDepartment(Department department){
        department.setEnabled(true);
        return  departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping ("/{id}")
    public ResultBean deleteDepartment(@PathVariable Integer  id){
        return  departmentService.deleteDepartment(id);
    }

}
