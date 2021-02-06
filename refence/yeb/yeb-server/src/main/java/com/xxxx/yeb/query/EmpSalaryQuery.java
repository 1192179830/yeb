package com.ybzn.yeb.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ybzn.yeb.pojo.Department;
import com.ybzn.yeb.pojo.Salary;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * auth:Administrator
 * time:2020/7/19 0019 11:10
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@ApiModel (value = "返回给前台的data数据", description = "")
public class EmpSalaryQuery {
    private Integer id;
    private String name;
    private String workID;
    private String email;
    private String phone;
    private Department department;
    private Salary salary;
}
