package com.xxxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.yeb.mapper.*;
import com.xxxx.yeb.pojo.*;
import com.xxxx.yeb.query.QueryEmployee;
import com.xxxx.yeb.service.IEmployeeService;
import com.xxxx.yeb.utils.AddStringUtil;
import com.xxxx.yeb.utils.AssertUtil;
import com.xxxx.yeb.utils.ReadExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl <EmployeeMapper, Employee> implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private NationMapper nationMapper;

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PoliticsStatusMapper politicsStatusMapper;

    @Resource
    private PositionMapper positionMapper;

    /**
     * 查询列表  所有员工
     *
     * @return
     */
    @Override
    public Map <String, Object> employeeList (QueryEmployee queryEmployee) {


        Page <Employee> page = new Page <>(queryEmployee.getCurrentPage(), queryEmployee.getSize());

        Map <String, Object> map = new HashMap <>();

        if (queryEmployee.getBeginDateScope() != null && !queryEmployee.getBeginDateScope().equals("")) {
            String[] time = queryEmployee.getBeginDateScope().split(",");
            String beginTime = time[0];
            String endTime = time[1];
            queryEmployee.setBeginTime(beginTime);
            queryEmployee.setEndTime(endTime);
        }

        IPage <Employee> iPage = employeeMapper.selectPagePlus(page, queryEmployee);

        map.put("data", iPage.getRecords());
        map.put("total", iPage.getTotal());
        return map;
    }

    /**
     * AddEmp 添加
     */
    @Override
    public void AddEmp (Employee employee) {

        //非空校验
        AssertUtil.isTrue(employeeMapper.insert(employee) < 1, "添加失败了");

    }

    /**
     * deleteEmp删除
     */
    @Override
    public void deleteEmp (Integer id) {
        //非空校验
        AssertUtil.isTrue(id == null, "待删除的记录不存在");
        Employee employee = employeeMapper.selectById(id);
        AssertUtil.isTrue(employee == null, "待删除记录不存在");

        AssertUtil.isTrue(employeeMapper.deleteById(id) < 1, "删除失败了");

    }

    /**
     * UpdateEmp修改
     */
    @Override
    public void UpdateEmp (Employee employee) {
        System.out.println(employee.toString());
        Integer id = employee.getId();
        AssertUtil.isTrue(id == null || employeeMapper.selectById(id) == null, "待修改用户记录不存在");
        //非空校验

        AssertUtil.isTrue(employeeMapper.update(employee, null) < 1, "修改失败了");

    }

    /**
     * 查询所有Nations 民族
     */
    @Override
    public List <Nation> initNations () {

        return nationMapper.selectList(null);
    }


    /**
     * 查询所有Joblevels 职称ID
     */
    @Override
    public List <Joblevel> initJoblevels () {
        return joblevelMapper.selectList(null);
    }


    /**
     * 查询所有Politicsstatus 政治面貌
     */
    @Override
    public List <PoliticsStatus> initPoliticsstatus () {
        return politicsStatusMapper.selectList(null);
    }

    /**
     * 查询所有Department 部门
     */

    @Override
    public List <Department> initDeps () {
        return departmentMapper.selectList(null);
    }


    /**
     * 查询所有initPositions 工作岗位
     */

    @Override
    public List <Position> initPositions () {
        return positionMapper.selectList(null);
    }


    /**
     * 获取工作ID
     *
     * @return
     */
    @Override
    public RespBean getMaxWorkID () {
        RespBean respBean = new RespBean();
        String wordID = AddStringUtil.addOne(employeeMapper.selectMaxWorkId());
        respBean.setObj(wordID);
        return respBean;
    }

    @Override
    public List <Employee> export () {
        Integer count = employeeMapper.selectCountEmp();
        Page page = new Page();
        page.setSize(count);
        QueryEmployee queryEmployee = new QueryEmployee();
        queryEmployee.setSize(count);
        IPage <Employee> iPage = employeeMapper.selectPagePlus(page, queryEmployee);
        List <Employee> list = iPage.getRecords();
        return list;
    }

    /**
     * @description: 导入Excel文件
     * @param: multipartFile
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/24 12:08
     */
    @Override
    @Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void importAll (MultipartFile multipartFile) throws Exception {


        // 创建读取Excel文件的对象
        ReadExcelUtil readExcelUtil = new ReadExcelUtil(multipartFile.getInputStream(), multipartFile.getName());

        // 获取Excel中的数据
        List <List <String>> lists = readExcelUtil.read();
        // 去除第一行，即Excel的标题
        lists.remove(0);

        List <Employee> employeeList = new ArrayList <>();
        // 循环，为Employee对象赋值
        // 外层遍历,Employee值的集合
        lists.forEach(emp -> {

            // 每一个Employee对象的成员属性的值的集合
            Employee employee = new Employee();

            employee.setName(emp.get(0));
            employee.setWorkID(emp.get(1));
            employee.setGender(emp.get(2));
            employee.setBirthday(LocalDate.parse(emp.get(3)));
            employee.setIdCard(emp.get(4));
            employee.setWedlock(emp.get(5));
            employee.setNationId(nationMapper.selectIdByName(emp.get(6)));
            employee.setNativePlace(emp.get(7));
            employee.setPoliticId(politicsStatusMapper.selectIdByName(emp.get(8)));
            employee.setEmail(emp.get(9));
            employee.setPhone(emp.get(10));
            employee.setAddress(emp.get(11));
            employee.setDepartmentId(departmentMapper.selectIdByName(emp.get(12)));
            employee.setJobLevelId(joblevelMapper.selectIdByName(emp.get(13)));
            employee.setPosId(positionMapper.selectIdByName(emp.get(14)));
            employee.setEngageForm(emp.get(15));
            employee.setTiptopDegree(emp.get(16));
            employee.setSchool(emp.get(17));
            employee.setSpecialty(emp.get(18));
            employee.setWorkState(emp.get(19));
            employee.setBeginDate(LocalDate.parse(emp.get(20)));
            employee.setConversionTime(LocalDate.parse(emp.get(21)));
            employee.setBeginContract(LocalDate.parse(emp.get(22)));
            employee.setEndContract(LocalDate.parse(emp.get(23)));
            employee.setContractTerm(Double.valueOf(emp.get(24)));

            // 将该对象添加到数据库
            employeeList.add(employee);
        });

        int rows = 0;

        for (Employee employee : employeeList) {
            int index = employeeMapper.insert(employee);
            rows += index;
        }

        AssertUtil.isTrue(rows != employeeList.size(), "导入数据失败！");

    }
}
