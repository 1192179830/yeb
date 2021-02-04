package com.xxxx.yeb;

import com.xxxx.yeb.exceptions.ParamsException;
import com.xxxx.yeb.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 全局异常拦截
 * @param: null
 * @return: {@link null}
 * @throws:
 * @author: hxxiapgy
 * @date: 2020/7/19 14:20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description: 捕获全局异常
     * @param: e
     * @param: request
     * @return: {@link Object}
     * @throws:
     * @author: hxxiapgy
     * @date: 2020/7/19 14:22
     */
    @ExceptionHandler (value = Exception.class)
    Object handleException (Exception e, HttpServletRequest request) {

        RespBean respBean = new RespBean();
        respBean.setCode(500);
        respBean.setMessage(e.getMessage());
        respBean.setObj(request.getRequestURL());

        return respBean;
    }

    /**
     * 功能描述: 处理自定义异常类
     *
     * @return
     */
    @ExceptionHandler (value = ParamsException.class)
    Object handleMyException (ParamsException p, HttpServletRequest request) {

        RespBean respBean = new RespBean();
        respBean.setCode(p.getCode());
        respBean.setMessage(p.getMessage());
        respBean.setObj(request.getRequestURL());

        return respBean;
    }


}
