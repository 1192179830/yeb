package com.ybzn.config.security.compont;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限控制
 * 判断用户角色
 * @author Hugo
 * @time 2021/1/24
 */
@Component
public class CustomUrlDetailsManager implements AccessDecisionManager {


    @Override
    public void decide (Authentication authentication, Object object, Collection <ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前url需要的角色
            String needRoles = configAttribute.getAttribute();
            //判断角色是否登录 即可访问的角色，此角色在CustomFilter中设置
            if("ROLE_LOGIN".equals(needRoles)){
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("未登录，请登录");
                }else
                {
                    return;
                }
            }
            // 判断角色角色是否为Url所需角色
            Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRoles)) {
                    return;
                }
            }
            // 若均不行，抛出异常
            throw new AccessDeniedException("权限不够，联系管理员!!");

        }
    }

    @Override
    public boolean supports (ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports (Class <?> clazz) {
        return false;
    }
}
