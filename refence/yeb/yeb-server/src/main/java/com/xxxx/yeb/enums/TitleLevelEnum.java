package com.ybzn.yeb.enums;

/**
 * @name: 职称等级
 * @author: hxxiapgy
 * @date: 2020/7/19 14:48
 */
public enum TitleLevelEnum implements BaseEnum {
    /**
     * 正高级
     */
    SENIOR(0, "正高级"),
    /**
     * 副高级
     */
    SUB_SENIOR(1, "副高级"),
    /**
     * 中级
     */
    MEDIUM(2, "中级"),
    /**
     * 初级
     */
    ELEMENTARY(3, "初级"),
    /**
     * 员级
     */
    CAGT(4, "员级");

    private Integer value;
    private String name;

    private TitleLevelEnum (Integer value, String name) {
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
