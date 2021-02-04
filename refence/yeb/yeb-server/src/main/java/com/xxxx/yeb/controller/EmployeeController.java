package com.xxxx.yeb.controller;


import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.query.QueryEmployee;
import com.xxxx.yeb.service.IDepartmentService;
import com.xxxx.yeb.service.IEmployeeService;
import com.xxxx.yeb.service.email.SendConfirmSyncService;
import com.xxxx.yeb.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@RestController
@RequestMapping ("/employee")
public class EmployeeController {


    @Autowired
    private IEmployeeService employeeService;

    @Resource
    private IDepartmentService departmentService;

    @Resource
    private SendConfirmSyncService sendConfirmSyncService;

    /**
     * 页面展示
     *
     * @param employee
     * @return
     */
    @ResponseBody
    @GetMapping ("/basic")
    public Map <String, Object> employeeList (QueryEmployee employee) {

        return employeeService.employeeList(employee);
    }

    @ResponseBody
    @RequestMapping ("/basic/maxWorkID")
    public RespBean getMaxWorkID () {
        return employeeService.getMaxWorkID();
    }


    @ResponseBody
    @RequestMapping ("/basic/positions")
    public List <Position> initPositions () {

        List <Position> positions = employeeService.initPositions();
        return positions;
    }


    /**
     * 查询所有民族
     *
     * @return
     */
    @ResponseBody
    @GetMapping ("basic/nations")
    public List <Nation> initNations () {


        return employeeService.initNations();
    }

    /**
     * 查询所有岗位
     *
     * @return
     */
    @ResponseBody
    @GetMapping ("/basic/joblevels")
    public List <Joblevel> initJoblevels () {

        return employeeService.initJoblevels();
    }


    /**
     * 查询所有政治面貌
     *
     * @return
     */
    @ResponseBody
    @GetMapping ("/basic/politicsstatus")
    public List <PoliticsStatus> initPoliticsstatus () {

        return employeeService.initPoliticsstatus();
    }

    /**
     * 查询所有部门
     *
     * @return
     */
    @ResponseBody
    @GetMapping ("/basic/deps")
    public List <Department> initDeps () {

        return departmentService.queryAllModules();
    }


    /**
     * 删除
     */
    @ResponseBody
    @DeleteMapping ("/basic/{id}")
    public RespBean deleteEmp (Employee employee) {
        employeeService.deleteEmp(employee.getId());

        return RespBean.success("删除成功");
    }

    /**
     * 新增
     */
    @ResponseBody
    @PostMapping ("/basic")
    public RespBean AddEmp (@RequestBody Employee employee) {
        sendConfirmSyncService.sendmq(employee.getEmail());
        employeeService.AddEmp(employee);
        return RespBean.success("添加成功");
    }


    /**
     * 修改
     */
    @ResponseBody
    @PutMapping ("/basic")
    public RespBean UpdateEmp (@RequestBody Employee employee) {

        employeeService.UpdateEmp(employee);
        return RespBean.success("修改成功");
    }

    /**
     * 导出数据
     */
    @GetMapping ("/basic/export")
    @ResponseBody
    public void exportAll (HttpServletRequest request, HttpServletResponse response) throws Exception {
        List <Employee> list = employeeService.export();
        //excel标题
        String[] title = {"员工姓名", "工号", "性别", "出生日期", "身份证号", "婚姻状况", "民族", "籍贯", "政治面貌",
                "电子邮件", "电话号码", "联系地址", "所属部门", "职称", "职位", "聘用形式", "最高学历", "毕业院校", "专业",
                "在职状态", "入职日期", "转正日期", "合同起始日期", "合同终止日期", "合同期限",};

        //excel文件名
        String fileName = "员工信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "员工信息表";

        String[][] content = new String[list.size()][25];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            Employee employee = list.get(i);
            content[i][0] = employee.getName();
            content[i][1] = employee.getWorkID();
            content[i][2] = employee.getGender();
            content[i][3] = employee.getBirthday().toString();
            content[i][4] = employee.getIdCard();
            content[i][5] = employee.getWedlock();
            content[i][6] = employee.getNation().getName();
            content[i][7] = employee.getNativePlace();
            content[i][8] = employee.getPoliticsStatus().getName();
            content[i][9] = employee.getEmail();
            content[i][10] = employee.getPhone();
            content[i][11] = employee.getAddress();
            content[i][12] = employee.getDepartment().getName();
            content[i][13] = employee.getJoblevel().getName();
            content[i][14] = employee.getPosition().getName();
            content[i][15] = employee.getEngageForm();
            content[i][16] = employee.getTiptopDegree();
            content[i][17] = employee.getSchool();
            content[i][18] = employee.getSpecialty();
            content[i][19] = employee.getWorkState();
            content[i][20] = employee.getBeginDate().toString();
            content[i][21] = employee.getConversionTime().toString();
            content[i][22] = employee.getBeginContract().toString();
            content[i][23] = employee.getEndContract().toString();
            content[i][24] = employee.getContractTerm().toString();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流方法
     */
    public void setResponseHeader (HttpServletResponse response, String fileName) {

        try {
            try {
                fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @description: 导入Excel文件
     * @param: multipartFile
     * @return: {@link RespBean}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/24 12:07
     */
    @PostMapping ("/basic/import")
    public RespBean importAll (@RequestParam ("file") MultipartFile multipartFile) throws Exception {
        employeeService.importAll(multipartFile);
        return RespBean.success("导入成功！");
    }

}
