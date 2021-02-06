package com.ybzn.yeb.controller;


import com.ybzn.yeb.pojo.Department;
import com.ybzn.yeb.pojo.RespBean;
import com.ybzn.yeb.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@RestController
@RequestMapping ("/system/basic/department/")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;


    /**
     * 查询部门树结构
     *
     * @return
     */
    @ResponseBody
    @GetMapping ("")///system/basic/department/
    public List <Department> department () {
        return departmentService.queryAllModules();
    }

    /**
     * 添加子部门
     */

    @RequestMapping (method = RequestMethod.POST, path = "/")
    @ApiOperation (value = "新增部门")
    public RespBean addOrUpdateDepartment (@RequestBody Department department) {
        RespBean respBean = departmentService.addDepartment(department);
        return respBean;
    }

    /**
     * 删除子部门
     */
    @RequestMapping (method = RequestMethod.DELETE, path = "/{id}")
    @ApiOperation (value = "删除部门")
    public RespBean deleteDepartment (@PathVariable ("id") Integer id) {
        departmentService.deleteDepartment(id);
        return RespBean.success("删除成功!!");
    }


}
