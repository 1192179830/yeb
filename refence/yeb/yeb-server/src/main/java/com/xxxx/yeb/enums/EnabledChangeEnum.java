package com.ybzn.yeb.enums;

/**
 * @description: 启用、禁用枚举
 * @author: hxxiapgy
 * @date: 2020/7/18 19:48
 * @version: v1.0
 */
public enum EnabledChangeEnum implements BaseEnum {

    /**
     * 禁用用户
     */
    DISABLE(0, "禁用用户操作"),
    /**
     * 启用用户
     */
    ENABLE(1, "启用用户操作"),
    /**
     * 用户已经被禁用
     */
    EABLED(2, "用户已经被禁用"),
    /**
     * 用户已经被启用
     */
    ENABLED(3, "用户已经被启用");

    private Integer value;
    private String name;

    private EnabledChangeEnum (Integer value, String name) {
        this.value = value;
        this.name = name;

    }

    @Override
    public Integer getValue () {
        return value;
    }

    @Override
    public String getName () {
        return name;
    }


}
