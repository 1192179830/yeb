package com.xxxx.yeb.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yeb.mapper.DepartmentMapper;
import com.xxxx.yeb.mapper.EmployeeMapper;
import com.xxxx.yeb.mapper.SalaryMapper;
import com.xxxx.yeb.pojo.Employee;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.pojo.Salary;
import com.xxxx.yeb.query.EmpQuery;
import com.xxxx.yeb.query.EmpSalaryQuery;

import com.xxxx.yeb.utils.AssertUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping ("/salary")
public class SalaryController {

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 初始化工资帐套列表
     *
     * @return
     */
    @RequestMapping (method = RequestMethod.GET, value = "/sob")
    public List <Salary> getSalarys () throws Exception {
        List <Salary> salaries = new ArrayList <>();
        salaries = salaryMapper.selectList(new QueryWrapper <Salary>());

        return salaries;
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping ("/sob/{id}")
    public RespBean deleteById (@PathVariable Integer id) {
        //返回给前台的数据
        Boolean resp = true;
        int line = 0;
        //到employee中查询是否有级联关系
        Integer cont = employeeMapper.contCommon(id);
        if (cont > 0) {
            return RespBean.error("该帐套有级联关系，无法删除");
        }
        line = salaryMapper.deleteById(id);

        if (line == 0) {
            return RespBean.error("删除失败");
        }
        return RespBean.success("删除成功");
    }

    /**
     * 添加
     *
     * @param req
     * @param res
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping (method = RequestMethod.POST, value = "/sob")
    public Boolean addSalary (HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // 返回给前台的数据
        Boolean resp = true;
        String s = getStringFromStream(req);
        Salary salary = objectMapper.readValue(s, Salary.class);
        String name = salary.getName();
        AssertUtil.isTrue(name == null, "添加帐套的名字不能为空");
        //工资帐套名不能重复
        int count = salaryMapper.countByName(name);
        AssertUtil.isTrue(count > 0, "工资帐套名不能重复");
        //工资帐套添加时间
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        salary.setCreateDate(sdf.parse(dateNowStr));
        int line = salaryMapper.insert(salary);
        if (line == 0) {
            resp = false;
            return resp;
        }
        return resp;
    }

    /**
     * 编辑
     *
     * @param req
     * @param res
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping (method = RequestMethod.PUT, value = "/sob")
    public Boolean editSalary (HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 返回给前台的数据
        Boolean resp = true;
        String s = getStringFromStream(req);
        Salary salary = objectMapper.readValue(s, Salary.class);
        int line = salaryMapper.updateById(salary);
        if (line == 0) {
            resp = false;
            return resp;
        }
        return resp;
    }

    /**
     * 后台要想从Request Payload中得到自己想要的数据,就要从流中来获取数据
     *
     * @param req
     * @return
     */
    private String getStringFromStream (HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0) {
                    nTotalRead = nTotalRead + nRead;
                }
            }
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping ("/sobcfg/salaries")
    public List <Salary> initSalaries () {
        List <Salary> salaries = new ArrayList <>();
        salaries = salaryMapper.selectList(new QueryWrapper <Salary>());
        return salaries;
    }

    @GetMapping ("/sobcfg")
    public EmpQuery initEmps (Integer currentPage, Integer size) {
        //返回的结果集
        EmpQuery empQuery = new EmpQuery();
        //返回的data
        List <EmpSalaryQuery> empSalaryQueryList = new ArrayList <EmpSalaryQuery>();

        //员工总数
        int total = 0;
        //员工列表
        List <Employee> employees = new ArrayList <>();
        //获取所有员工
        IPage <Employee> page = new Page <>(currentPage, size);
        IPage <Employee> empPage = employeeMapper.selectPage(page, null);
        //获取employee表中的所有员工信息列表
        List <Employee> emps = empPage.getRecords();
        for (Employee e :
                emps) {
            EmpSalaryQuery empSalaryQuery = new EmpSalaryQuery();
            empSalaryQuery.setId(e.getId());
            empSalaryQuery.setName(e.getName());
            empSalaryQuery.setWorkID(e.getWorkID());
            empSalaryQuery.setEmail(e.getEmail());
            empSalaryQuery.setPhone(e.getPhone());
            empSalaryQuery.setDepartment(departmentMapper.selectById(e.getDepartmentId()));
            empSalaryQuery.setSalary(salaryMapper.selectById(e.getSalaryId()));
            empSalaryQueryList.add(empSalaryQuery);
        }
        //获取员工总数
        //total= (int) empPage.getTotal();
        total = employeeMapper.selectCount(null);
        System.out.println(total);
        empQuery.setData(empSalaryQueryList);
        empQuery.setTotal(total);
        return empQuery;
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/sobcfg")
    public Boolean putRequest (Integer eid, Integer sid) {
        Boolean flag = true;
        //根据id获取emp表中的数据
        Employee employee = employeeMapper.selectById(eid);
        //修改获取的雇员数据
        employee.setSalaryId(sid);
        //将修改后的雇员数据修改到表中,返回受影响行数
        int i = employeeMapper.updateById(employee);
        if (i <= 0) {
            flag = false;
            return flag;
        }
        return flag;
    }
}
