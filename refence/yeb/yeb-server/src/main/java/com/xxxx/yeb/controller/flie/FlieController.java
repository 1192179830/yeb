package com.xxxx.yeb.controller.flie;

import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.pojo.Employee;
import com.xxxx.yeb.service.flie.FlieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * auth:Administrator
 * time:2020/7/20 0020 09:24
 */
@Controller

public class FlieController {
    @Resource
    private FlieService fileService;

    @Resource
    private EmployeeMapper employeeMapper;


    @GetMapping ("/export")
    @ApiOperation ("导出带数据的excel")
    public void export () {
        Integer total = employeeMapper.contAll();
        ArrayList <Employee> employees = employeeMapper.selectAll();
        fileService.deriveTable(total, employees);
    }

    @PostMapping ("/import")
    @ApiOperation ("导入带数据的excel")
    public void enter () throws Exception {
        fileService.importTable();
    }

}


