package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    // RedisTemplate 编写配置类

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());

        // 设置value的序列化方式. json是结构化数据，易读取
        template.setValueSerializer(RedisSerializer.json());

        // 设置hash 的 key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());

        // 设置hash 的 value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        // 设置完以后， 触发生效
        template.afterPropertiesSet();

        return template;
    }

}
