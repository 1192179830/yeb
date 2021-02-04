package com.xxxx.yeb.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yeb.pojo.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足时，自定义返回结果
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Component
public class RestFulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle (HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RespBean respBean = RespBean.error("权限不足，请联系管理员!");
        respBean.setCode(403);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
