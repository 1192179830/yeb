package com.ybzn.utils;

import com.ybzn.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 * @author Hugo
 * @time 2021/2/2
 */
public class AdminUtils {

    /**
     * 获取当前登录操作员
     * @return
     */
    public static Admin getCurrentAdmin(){
        return ((Admin)(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}
