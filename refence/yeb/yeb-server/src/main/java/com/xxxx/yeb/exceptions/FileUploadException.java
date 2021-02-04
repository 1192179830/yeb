package com.xxxx.yeb.exceptions;

/**
 * @description: 自定义文件上传异常
 * @author: hxxiapgy
 * @date: 2020/7/20 12:14
 * @version: v1.0
 */
public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = -7997068285681115559L;

    private Integer code = 400;
    private String msg = "文件上传错误!";

    public FileUploadException () {
        super("文件上传错误!");
    }

    public FileUploadException (String msg) {
        super(msg);
        this.msg = msg;
    }

    public FileUploadException (Integer code) {
        super("文件上传错误!");
        this.code = code;
    }

    public FileUploadException (Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode () {
        return code;
    }

    public void setCode (Integer code) {
        this.code = code;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }

}
