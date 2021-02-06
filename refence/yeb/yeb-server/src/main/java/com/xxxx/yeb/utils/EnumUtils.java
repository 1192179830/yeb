package com.ybzn.yeb.utils;

import com.ybzn.yeb.enums.BaseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 枚举工具类
 * @author: hxxiapgy
 * @date: 2020/7/19 14:56
 */
public class EnumUtils {

    /**
     * 判断枚举名是否存在于指定枚举数组中
     *
     * @param enums 枚举数组
     * @param name  枚举名
     * @return boolean
     */
    public static boolean isExist (BaseEnum[] enums, String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        for (BaseEnum e : enums) {
            if (name.equals(e.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 根据枚举值获取其对应的name
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @return 枚举值获取其对应的name
     */
    public static String getNameByValue (BaseEnum[] enums, Integer value) {
        if (value == null) {
            return "";
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return e.getName();
            }
        }
        return "";
    }

    /**
     * @description: 根据枚举名获取对应的value
     * @param: enums
     * @param: value
     * @return: {@link Integer}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/19 15:15
     */
    public static Integer getValueByName (BaseEnum[] enums, Integer value) {
        if (value == null) {
            return null;
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return e.getValue();
            }
        }
        return null;
    }

}
