package com.ybzn.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ybzn.pojo.*;
import com.ybzn.service.*;
import com.ybzn.utils.ResultBean;
import com.ybzn.utils.ResultPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) throws IOException {
        List <Employee> employeeList = employeeService.getEmployee(null);
        ExportParams params =new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, employeeList);
        ServletOutputStream outputStream=null;
        try {
            //用流的形式传输
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode("员工表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=outputStream)
            {
                outputStream.close();
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public ResultBean importEmployee(MultipartFile file){
        ImportParams params =new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List <Nation> nationList = nationService.list();
        List <PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List <Department> departmentList = departmentService.list();
        List <Joblevel> joblevelList = joblevelService.list();
        List <Position> positionList = positionService.list();

        try {
            List <Employee> employeeList = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            for (Employee employee : employeeList) {
                //通过比较hashcode的值来进行获取，这个方法很关键！原本要查5次数据库的，现在只需要查一次数据库就好了
                //设置民族Id
                int indexOfNation = nationList.indexOf(new Nation(employee.getNation().getName()));
                Integer nationId = nationList.get(indexOfNation).getId();
                employee.setNationId(nationId);
                //下面几个都是一样的思路了

                //设置职位Id
                int indexOfPos = politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()));
                Integer posId = politicsStatusList.get(indexOfPos).getId();
                employee.setPosId(posId);
                //设置部门Id  //写成一行
               employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department( employee.getDepartment().getName()))).getId());
                //设置民族Id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                //设置政治面貌Id
                employee.setPoliticId(positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId());
            }
            if (employeeService.saveBatch(employeeList)) {
                return ResultBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBean.error("导入失败！");
    }


}
