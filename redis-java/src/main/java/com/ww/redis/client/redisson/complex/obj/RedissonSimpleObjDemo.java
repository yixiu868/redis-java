package com.ww.redis.client.redisson.complex.obj;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.Arrays;

/**
 * @author xiaohua
 * @description TODO
 * @date 2021-9-6 23:14
 */
public class RedissonSimpleObjDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new StringCodec());
        RedissonClient redisson = Redisson.create(config);
        RMap<String, String> map = redisson.getMap("dog");

        map.put("name", "小白");
        map.put("color", "纯白色");
        System.out.println("存入dog成功");

        System.out.println("获取存入dog信息");
        System.out.println(map.get("name"));
        System.out.println(map.get("color"));

        redisson.shutdown();
    }
}
