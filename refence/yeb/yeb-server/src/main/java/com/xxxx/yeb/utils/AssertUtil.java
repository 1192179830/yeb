package com.xxxx.yeb.utils;

import com.xxxx.yeb.exceptions.FileUploadException;
import com.xxxx.yeb.exceptions.ParamsException;

/**
 * @author hxxiapgy
 */
public class AssertUtil {

    public static void isTrue (Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }


    /**
     * @description: 文件上传异常
     * @param: flag
     * @param: msg
     * @return:
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/20 12:13
     */
    public static void fileException (Boolean flag, String msg) {
        if (flag) {
            throw new FileUploadException(msg);
        }
    }

}
