package com.ybzn.yeb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybzn.yeb.pojo.Employee;
import com.ybzn.yeb.query.QueryEmployee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Repository
public interface EmployeeMapper extends BaseMapper <Employee> {
    /**
     * 获取帐套与员工的级联总数
     */
    public Integer contCommon (Integer id);

    public Integer contAll ();

    public ArrayList <Employee> selectAll ();

    /**
     * 查询该职位下的总人数
     *
     * @param id
     * @return
     */
    @Select ("SELECT COUNT(1) FROM t_employee WHERE posId = #{id}")
    int selectcount (Integer id);


    Employee selectByName (String name);

    String selectMaxWorkId ();

    IPage <Employee> selectPagePlus (@Param ("page") Page page, @Param ("queryEmployee") QueryEmployee queryEmployee);

    int selectCountEmp ();


    int insertBatch (List <Employee> employeeList);
}
