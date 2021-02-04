package com.ybzn.config.security.compont;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybzn.utils.ResultBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当访问接口没有权限的时候，自定义返回结果
 * @author Hugo
 * @time 2021/1/22
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle (HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ResultBean resultBean = ResultBean.error("权限不够！");
        resultBean.setCode(403);
        out.write(new ObjectMapper().writeValueAsString(resultBean));
        out.flush();
        out.close();
    }
}
