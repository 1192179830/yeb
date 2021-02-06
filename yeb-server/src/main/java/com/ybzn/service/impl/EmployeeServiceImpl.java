package com.ybzn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybzn.pojo.Employee;
import com.ybzn.mapper.EmployeeMapper;
import com.ybzn.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybzn.utils.ResultBean;
import com.ybzn.utils.ResultPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询 员工信息,这个很关键！！
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public ResultPageBean getEmployeeByPage (Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page= new Page(currentPage,size);
        IPage <Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);

        ResultPageBean resultPageBean =new ResultPageBean();
        resultPageBean.setData(employeeByPage.getRecords());
        resultPageBean.setTotal(employeeByPage.getTotal());
        return resultPageBean;

    }

    /**
     * 获取工号,先获取原来的工号，在原来的基础上+1，就是新工号
     * @return
     */
    @Override
    public ResultBean maxWorkId () {
        List <Map <String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper <Employee>().select("max(workID)"));
        int i = Integer.parseInt(maps.get(0).get("max(workID)").toString())+1;
        String maxWorkId = String.format("%08d", i);//格式化一下
        return ResultBean.success(null,maxWorkId);
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @Override
    public ResultBean addEmp (Employee employee) {
        //关键：处理 合同日期,保留两位小数
        //********************
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat =new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        //********************
        if(1==employeeMapper.insert(employee)){
            return ResultBean.success("添加成功!!");
        }
        return ResultBean.error("添加失败！");
    }
}
