package com.deloitte.cdeidutil.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories
public class RedisClientConfig {

  @Value("${spring.redis.host}")
  private String redisHost;
  @Value("${spring.redis.port}")
  private int redisPort;
  @Value("${spring.redis.password}")
  private String redisPassword;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration =
        new RedisStandaloneConfiguration(redisHost, redisPort);
    redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
    JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration =
        (JedisClientConfiguration.JedisClientConfigurationBuilder) JedisClientConfiguration
            .builder().useSsl().and().usePooling();
    jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout
    JedisConnectionFactory jedisConFactory =
        new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    return jedisConFactory;
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }
}
