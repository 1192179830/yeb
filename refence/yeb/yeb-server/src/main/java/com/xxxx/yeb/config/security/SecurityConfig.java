package com.xxxx.yeb.config.security;

import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private RestFulAccessDeniedHandler restFulAccessDeniedHandler;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private CustomUrlAccessDecisionManager customUrlAccessDecisionManager;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "/favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha",
                "/export",
                "/ws/**",
                "/email/**",
                "/topic/**"
        );
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        //使用JWT，不需要csrf
        http.csrf().disable()
                //不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //所有请求都必须被认证
                .anyRequest()
                .authenticated()
                //动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor <FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess (O object) {
                        object.setSecurityMetadataSource(customFilter);
                        object.setAccessDecisionManager(customUrlAccessDecisionManager);
                        return object;
                    }
                })
                .and()
                //禁用缓存
                .headers()
                .cacheControl();
        //添加JWT 登录授权拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //自定义异常返回结果 添加自定义 未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restFulAccessDeniedHandler)
                .authenticationEntryPoint(restfulAuthenticationEntryPoint);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService () {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (null == admin) {
                throw new UsernameNotFoundException("用户名或密码错误！");
            }
            admin.setRoles(adminService.getRoles(admin.getId()));
            return admin;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter () {
        return new JwtAuthenticationTokenFilter();
    }

}
