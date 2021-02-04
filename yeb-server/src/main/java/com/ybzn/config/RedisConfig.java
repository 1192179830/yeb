package com.ybzn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author Hugo
 * @time 2021/1/24
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate <String,Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate =new RedisTemplate <>();
        //String 类型 key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //String 类型 value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // Hash类型 序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


}
