package com.urlshortener.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class RedisConfig {


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        System.out.println("Redis Connection Factory start called");
        LettuceConnectionFactory factory = new LettuceConnectionFactory("redis", 6379);
        factory.setTimeout(5000);  // Timeout in milliseconds
        System.out.println("Redis Connection Factory end");
        return factory;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        System.out.println("Redis Template start called");
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(String.class));
return redisTemplate;

    }
}
