package com.ybzn.yeb.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QueryEmployee {

    private String name;

    private long currentPage = 1;

    private long size = 10;

    // 政治面貌
    private Integer politicId;

    //所属部门
    private Integer departmentId;

    //民族
    private Integer nationId;

    //职位
    private Integer posId;

    //职称
    private Integer jobLevelId;

    //聘用形式
    private String engageForm;

    //入职日期    格式: 2020-07-22 , 2020-08-18  范围里
    private String beginDateScope;

    private String beginTime;


    private String endTime;

}
