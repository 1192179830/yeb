package com.ybzn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @author Hugo
 * @time 2021/1/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     *
     * @param message
     * @return
     */
    public static ResultBean success(String message){
        return new ResultBean(200,message,null);
    }

    /**
     * 成功返回对象
     * @param message
     * @param obj
     * @return
     */
    public static ResultBean success(String message,Object obj){
        return new ResultBean(200,message,obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static ResultBean error(String message){
        return new ResultBean(500,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param obj
     * @return
     */
    public static ResultBean error(String message,Object obj){
        return new ResultBean(500,message,obj);
    }


}
