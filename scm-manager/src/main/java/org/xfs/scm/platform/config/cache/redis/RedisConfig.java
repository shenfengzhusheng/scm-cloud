package org.xfs.scm.platform.config.cache.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;


/**
 * Redis缓存配置类
 */
@Configuration
@RefreshScope
public class RedisConfig  extends CachingConfigurerSupport {

    @Value("${scm.redis.host}")
    private String host;

    @Value("${scm.redis.port}")
    private int port;

    @Value("${scm.redis.password}")
    private String password;

    @Value("${scm.redis.timeout}")
    private int timeout;

    @Value("${scm.redis.database}")
    private int database;



    @Bean(destroyMethod = "destroy")
    public JedisPool jedisPool(){
        if(this.host!=null &&this.host!=""){
            GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxWaitMillis(1000);
            genericObjectPoolConfig.setMaxIdle(20);
            genericObjectPoolConfig.setMaxTotal(20000);
            JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,this.host,this.port,this.timeout,this.password,6);
            return jedisPool;
        }
        return null;
    }

    //自定义缓存key生成策略
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for(Object obj:params){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    //缓存管理器
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(this.timeout);
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);//设置序列化工具
        template.afterPropertiesSet();
        return template;
    }
    private void setSerializer(StringRedisTemplate template){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}