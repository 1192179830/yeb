package com.ybzn.controller;


import com.ybzn.pojo.*;
import com.ybzn.service.*;
import com.ybzn.utils.ResultBean;
import com.ybzn.utils.ResultPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.apache.commons.collections.ResettableIterator;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
@Api(tags = "员工管理 Controller")
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @Autowired
    private IJoblevelService joblevelService;

    @Autowired
    private INationService nationService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired IPositionService positionService;

    /**
     * 这个接口很有水平
     */
    @ApiOperation(value = "查询所有员工")
    @GetMapping("/")
    public ResultPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      Employee employee,
                                      LocalDate[] beginDateScope){
        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value ="获取所有职称")
    @GetMapping("/joblevel")
    public List<Joblevel> getAllJoblevel(){
        return joblevelService.list();
    }

    @ApiOperation(value ="获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value ="获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value ="获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.list();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkId")
    public ResultBean maxWorkId(){
        return employeeService.maxWorkId();//获取最大工号，防止重复
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public ResultBean addEmp(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }


    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public ResultBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return ResultBean.success("更新成功！");
        }
        return ResultBean.error("更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/")
    public ResultBean DeleteEmp(@RequestBody Employee employee){
        if(employeeService.removeById(employee)){
            return ResultBean.success("删除成功！");
        }
        return ResultBean.error("删除失败！");
    }




}
