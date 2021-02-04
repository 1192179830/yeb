package com.ybzn.config.security.compont;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybzn.utils.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者Token失效的时候访问接口，自定义的返回结果
 * @author Hugo
 * @time 2021/1/22
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ResultBean resultBean = ResultBean.error("未登录，请登录！");
        resultBean.setCode(401);
        writer.write(new ObjectMapper().writeValueAsString(resultBean));
        writer.flush();
        writer.close();
    }
}
