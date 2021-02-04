package com.xxxx.yeb.chat;

import com.xxxx.yeb.config.security.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.Resource;

/**
 * @author hxxiapgy
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Value ("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value ("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public void configureMessageBroker (MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个，配置代理目的地前缀为/queue,可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }

    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry) {

        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 输入通道参数配置
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel (ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message <?> preSend (Message <?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为连接，如果是，需要获取token，并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)) {
                        String authToken = token.substring(tokenHead.length());
                        String username = jwtTokenUtil.getUserNameFormToken(authToken);
                        //token中存在用户名
                        if (!StringUtils.isEmpty(username)) {
                            //登录
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            //验证token是否有效，重新设置用户对象
                            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                                userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }


}
