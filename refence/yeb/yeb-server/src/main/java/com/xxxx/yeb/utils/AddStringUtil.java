package com.xxxx.yeb.utils;

public class AddStringUtil {
    public static String addOne (String testStr) {
        String[] strs = testStr.split("[^0-9]");//根据不是数字的字符拆分字符串
        String numStr = strs[strs.length - 1];//取出最后一组数字
        if (numStr != null && numStr.length() > 0) {//如果最后一组没有数字(也就是不以数字结尾)，抛NumberFormatException异常
            int n = numStr.length();//取出字符串的长度
            int num = Integer.parseInt(numStr) + 1;//将该数字加一
            String added = String.valueOf(num);
            n = Math.min(n, added.length());
            //拼接字符串
            return testStr.subSequence(0, testStr.length() - n) + added;
        } else {
            throw new NumberFormatException();


        }
    }
}
