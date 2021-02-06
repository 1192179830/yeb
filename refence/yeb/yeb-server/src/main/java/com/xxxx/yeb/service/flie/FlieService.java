package com.ybzn.yeb.service.flie;

import com.ybzn.yeb.mapper.*;
import com.ybzn.yeb.pojo.Employee;
import com.ybzn.yeb.query.EmployeeQuery;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * auth:Administrator
 * time:2020/7/20 0020 09:27
 */
@Service
public class FlieService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private NationMapper nationMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private PoliticsStatusMapper politicsStatusMapper;




    /*  public List<SingleHead> userList() {
            List<SingleHead> singleHeads = new ArrayList<>();
            for (int i = 0; i <100 ; i++) {
                SingleHead singleHead = new SingleHead();
                singleHead.setUserAge(i);
                singleHead.setUserName("第"+i+"个");
                singleHeads.add(singleHead);
            }
            return singleHeads;
        }*/

    //导出
    public void deriveTable (Integer total, List <Employee> employees) {
        // 创建Excel文件对应的对象
        HSSFWorkbook hwk = new HSSFWorkbook();
        // 创建一个sheet表名
        HSSFSheet hssfSheet = hwk.createSheet("员工资料表");

        // 通过sheet创建一盒row（行） 范围0-65535
        // List<HSSFRow> hssfRows = new ArrayList<>();
        //创建第一行数据
        HSSFRow hssfRow1 = hssfSheet.createRow(0);
        //
        hssfRow1.createCell(0).setCellValue("姓名");
        hssfRow1.createCell(1).setCellValue("工号");
        hssfRow1.createCell(2).setCellValue("性别");
        hssfRow1.createCell(3).setCellValue("出生日期");
        hssfRow1.createCell(4).setCellValue("身份证号码");
        hssfRow1.createCell(5).setCellValue("婚姻状况");
        hssfRow1.createCell(6).setCellValue("民族");
        hssfRow1.createCell(7).setCellValue("籍贯");
        hssfRow1.createCell(8).setCellValue("政治面貌");
        hssfRow1.createCell(9).setCellValue("电子邮件");
        hssfRow1.createCell(10).setCellValue("电话号码");
        hssfRow1.createCell(11).setCellValue("联系地址");
        hssfRow1.createCell(12).setCellValue("所属部门");
        hssfRow1.createCell(13).setCellValue("职称");
        hssfRow1.createCell(14).setCellValue("职位");
        hssfRow1.createCell(15).setCellValue("聘用形式");
        hssfRow1.createCell(16).setCellValue("最高学历");
        hssfRow1.createCell(17).setCellValue("毕业院校");
        hssfRow1.createCell(18).setCellValue("专业");
        hssfRow1.createCell(19).setCellValue("在职状态");
        hssfRow1.createCell(20).setCellValue("入职日期");
        hssfRow1.createCell(21).setCellValue("转正日期");
        hssfRow1.createCell(22).setCellValue("合同起始日期");
        hssfRow1.createCell(23).setCellValue("合同截止日期");
        hssfRow1.createCell(24).setCellValue("合同期限");
        //共有total条数据，第一行已经建好，创建total+1行表格
        for (int i = 1; i < total + 1; i++) {
            //取第i-1条数据
            Employee employee = employees.get(i - 1);
            HSSFRow hssfRow = hssfSheet.createRow(i);

            hssfRow.createCell(0).setCellValue(employee.getName());
            hssfRow.createCell(1).setCellValue(employee.getId());
            hssfRow.createCell(2).setCellValue(employee.getGender());
            hssfRow.createCell(3).setCellValue(employee.getBirthday().toString());
            hssfRow.createCell(4).setCellValue(employee.getIdCard());
            hssfRow.createCell(5).setCellValue(employee.getWedlock());
            //查出nation的名字
            hssfRow.createCell(6).setCellValue(nationMapper.selectById(employee.getNationId()).getName());
            hssfRow.createCell(7).setCellValue(employee.getNativePlace());
            hssfRow.createCell(8).setCellValue(politicsStatusMapper.selectById(employee.getPoliticId()).getName());
            hssfRow.createCell(9).setCellValue(employee.getEmail());
            hssfRow.createCell(10).setCellValue(employee.getPhone());
            hssfRow.createCell(11).setCellValue(employee.getAddress());
            hssfRow.createCell(12).setCellValue(departmentMapper.selectById(employee.getDepartmentId()).getName());
            hssfRow.createCell(13).setCellValue(joblevelMapper.selectById(employee.getJobLevelId()).getName());
            hssfRow.createCell(14).setCellValue(positionMapper.selectById(employee.getPosId()).getName());
            hssfRow.createCell(15).setCellValue(employee.getEngageForm());
            hssfRow.createCell(16).setCellValue(employee.getTiptopDegree());
            hssfRow.createCell(17).setCellValue(employee.getSchool());
            hssfRow.createCell(18).setCellValue(employee.getSpecialty());
            hssfRow.createCell(19).setCellValue(employee.getWorkState());
            hssfRow.createCell(20).setCellValue(employee.getBeginDate().toString());
            hssfRow.createCell(21).setCellValue(employee.getConversionTime().toString());
            hssfRow.createCell(22).setCellValue(employee.getBeginContract().toString());
            hssfRow.createCell(23).setCellValue(employee.getEndContract().toString());
            hssfRow.createCell(24).setCellValue(employee.getContractTerm());
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:/Users/Administrator/Desktop/");
            hwk.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //导入
    public void importTable () throws Exception {
        List <EmployeeQuery> employeeQueries = new ArrayList <>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/Administrator/Desktop/Excel.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            HSSFWorkbook hwk = new HSSFWorkbook(fis);
            HSSFSheet sheet = hwk.getSheetAt(0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //遍历表格中所有的行 sheet.getLastCellNum 是获取最后一个不为空的行是第几个。
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                if (sheet.getRow(i) == null) {
                    continue;
                }
                EmployeeQuery employeeQuery = new EmployeeQuery();
                //字符串直接传入,姓名
                employeeQuery.setName(sheet.getRow(i).getCell(0).getStringCellValue());
                //用double接数字，再强转成int，工号
                employeeQuery.setId((int) sheet.getRow(i).getCell(1).getNumericCellValue());
                //性别
                employeeQuery.setGender(sheet.getRow(i).getCell(2).getStringCellValue());
                //String转localDate，出生日期
                employeeQuery.setBirthday(LocalDate.parse(sheet.getRow(i).getCell(3).getStringCellValue()));
                //身份证号
                employeeQuery.setIdCard(sheet.getRow(i).getCell(4).getStringCellValue());
                //婚姻情况
                employeeQuery.setWedlock(sheet.getRow(i).getCell(5).getStringCellValue());
                //民族
                employeeQuery.setNationName(sheet.getRow(i).getCell(6).getStringCellValue());
                //籍贯
                employeeQuery.setNativePlace(sheet.getRow(i).getCell(7).getStringCellValue());
                //政治面貌
                employeeQuery.setPoliticName(sheet.getRow(i).getCell(8).getStringCellValue());
                //邮件
                employeeQuery.setEmail(sheet.getRow(i).getCell(9).getStringCellValue());
                //电话
                employeeQuery.setPhone(sheet.getRow(i).getCell(10).getStringCellValue());
                //地址
                employeeQuery.setAddress(sheet.getRow(i).getCell(11).getStringCellValue());
                //所属部门
                employeeQuery.setDepartmentName(sheet.getRow(i).getCell(12).getStringCellValue());
                //职称
                employeeQuery.setJobLevelName(sheet.getRow(i).getCell(13).getStringCellValue());
                //职位
                employeeQuery.setPosName(sheet.getRow(i).getCell(14).getStringCellValue());
                //聘用形式
                employeeQuery.setEngageForm(sheet.getRow(i).getCell(15).getStringCellValue());
                //学历
                employeeQuery.setTiptopDegree(sheet.getRow(i).getCell(16).getStringCellValue());

                //学校
                employeeQuery.setSchool(sheet.getRow(i).getCell(17).getStringCellValue());
                //专业
                employeeQuery.setSpecialty(sheet.getRow(i).getCell(18).getStringCellValue());
                //在职状态
                employeeQuery.setWorkState(sheet.getRow(i).getCell(19).getStringCellValue());
                //入职日期
                employeeQuery.setBeginDate(LocalDate.parse(sheet.getRow(i).getCell(20).getStringCellValue()));
                //转正日期
                employeeQuery.setConversionTime(LocalDate.parse(sheet.getRow(i).getCell(21).getStringCellValue()));
                //合同起始时间
                employeeQuery.setBeginContract(LocalDate.parse(sheet.getRow(i).getCell(22).getStringCellValue()));
                //合同结束时间
                employeeQuery.setEndContract(LocalDate.parse(sheet.getRow(i).getCell(23).getStringCellValue()));
                //合同期限
                employeeQuery.setContractTerm(sheet.getRow(i).getCell(24).getNumericCellValue());
                employeeQueries.add(employeeQuery);
            }
            //批量把employeeQuerie换成Employee对象
            for (EmployeeQuery employeeQuery :
                    employeeQueries) {
                Employee employee = new Employee();
                employee.setName(employeeQuery.getName());
                employee.setGender(employeeQuery.getGender());
                employee.setBirthday(employeeQuery.getBirthday());
                employee.setIdCard(employeeQuery.getIdCard());
                employee.setWedlock(employeeQuery.getWedlock());
                employee.setNationId(nationMapper.selectIdByName(employeeQuery.getNationName()));
                employee.setNativePlace(employeeQuery.getNativePlace());
                employee.setPoliticId(politicsStatusMapper.selectIdByName(employeeQuery.getPoliticName()));
                employee.setEmail(employeeQuery.getEmail());
                employee.setPhone(employeeQuery.getPhone());
                employee.setAddress(employeeQuery.getAddress());
                employee.setDepartmentId(departmentMapper.selectIdByName(employeeQuery.getDepartmentName()));
                employee.setJobLevelId(joblevelMapper.selectIdByName(employeeQuery.getJobLevelName()));
                employee.setPosId(positionMapper.selectIdByName(employeeQuery.getPosName()));
                employee.setEngageForm(employeeQuery.getEngageForm());
                employee.setTiptopDegree(employeeQuery.getTiptopDegree());
                employee.setSpecialty(employeeQuery.getSpecialty());
                employee.setSchool(employeeQuery.getSchool());
                employee.setBeginDate(employeeQuery.getBeginDate());
                employee.setWorkState(employeeQuery.getWorkState());
                employee.setContractTerm(employeeQuery.getContractTerm());
                employee.setConversionTime(employeeQuery.getConversionTime());
                employee.setBeginContract(employeeQuery.getBeginContract());
                employee.setEndContract(employeeQuery.getEndContract());
                int insert = employeeMapper.insert(employee);
                System.out.println(insert);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
