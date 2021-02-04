package com.ybzn.config.security;

import com.ybzn.config.security.compont.*;
import com.ybzn.pojo.Admin;
import com.ybzn.service.IAdminService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类 09结尾
 *
 * @author Hugo
 * @time 2021/1/20
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private CustomFilter customFilter;

    @Autowired
    private CustomUrlDetailsManager customUrlDetailsManager;

    @Autowired
    private IAdminService adminService;


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoders () {
        return new BCryptPasswordEncoder();//加密
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf()
                .disable()//基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/login","/logout")//允许登录 这一行可以删除 下面配置了
//                .permitAll() //这一行可以删除
                .anyRequest() //除了上面的都要拦截
                .authenticated()
                //动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor <FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess (O object) {
                        object.setAccessDecisionManager(customUrlDetailsManager);
                        object.setSecurityMetadataSource(customFilter);
                        return object;
                    }
                })
                .and()
                .headers()
                .cacheControl()
                ;
        //添加JWT 登录授权拦截器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义 未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }


    /**
     * 放行一些路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
              "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "index.html",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captChar"
        );
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Admin admin = iAdminService.getAdminByUserName(username);
            if(null!= admin){
                admin.setRoles(adminService.getRoles(admin.getId()));
                return admin;
            }
            return null;
        };
    }



    /**
     * 返回一个新的security JWT拦截器
     * @return
     */
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }
}
