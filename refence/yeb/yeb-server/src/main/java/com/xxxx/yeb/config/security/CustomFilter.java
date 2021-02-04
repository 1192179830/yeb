package com.xxxx.yeb.config.security;

import com.xxxx.yeb.pojo.Menu;
import com.xxxx.yeb.pojo.Role;
import com.xxxx.yeb.service.IMenuService;
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
 * 权限控制
 * 根据URL查询对应角色
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection <ConfigAttribute> getAttributes (Object object) throws IllegalArgumentException {
        //请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List <Menu> menus = menuService.getMenusWithRole();
        for (Menu menu : menus) {
            //比较菜单url和请求url
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                //如果相等，放入角色
                String[] roles = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        //没有匹配的URL，默认登录就可以用
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
