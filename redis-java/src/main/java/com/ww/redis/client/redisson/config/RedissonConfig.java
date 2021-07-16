package com.ww.redis.client.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohua
 * @version 1.0
 * @description redisson配置
 * @date 2021-7-7 23:57
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient configRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new StringCodec());
        // 设置看门狗时间,不配置默认30000
        config.setLockWatchdogTimeout(12000);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
