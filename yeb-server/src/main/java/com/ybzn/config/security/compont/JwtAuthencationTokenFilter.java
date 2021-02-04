package com.ybzn.config.security.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hugo
 * @time 2021/1/20
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头
        String authHeader = request.getHeader(tokenHeader);
        //存在token
        if (null != authHeader && authHeader.startsWith(tokenHead)) {
            String token = authHeader.substring(tokenHead.length());
            //获取用户名
            String username = jwtTokenUtil.getUserNameFormToken(token);
            //存在token但是未登录
            if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
                //登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //判断token是否有效
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    //更新Security用户对象
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }



}
