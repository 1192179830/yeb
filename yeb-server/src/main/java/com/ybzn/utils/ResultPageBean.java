package com.ybzn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Hugo
 * @time 2021/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPageBean {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据
     */
    private List <?> data;
}
