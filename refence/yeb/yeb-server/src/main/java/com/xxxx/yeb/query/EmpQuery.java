package com.xxxx.yeb.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * auth:Administrator
 * time:2020/7/19 0019 11:15
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
@ApiModel (value = "返回给前台的结果集", description = "")
public class EmpQuery {
    private List <EmpSalaryQuery> data;
    private Integer total;
}
