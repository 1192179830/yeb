package com.ybzn.config.security.compont;

import com.ybzn.pojo.Menu;
import com.ybzn.pojo.Role;
import com.ybzn.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限拦截器
 * @author Hugo
 * @time 2021/1/24
 */
@Component
public class CustomFilter  implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher =new AntPathMatcher();

    @Override
    public Collection <ConfigAttribute> getAttributes (Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List <Menu> menus = menuService.getMenuWithRole();
        for (Menu menu: menus) {
            if(antPathMatcher.match(menu.getUrl(), requestUrl)){
                String[] array = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(array);
            }

        }
        // 没有匹配的url默认登录即可
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection <ConfigAttribute> getAllConfigAttributes () {
        return null;
    }

    @Override
    public boolean supports (Class <?> clazz) {
        return false;
    }
}
