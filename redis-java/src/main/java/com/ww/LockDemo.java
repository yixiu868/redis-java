package com.ww;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohua
 * @description TODO
 * @date 2021-8-31 15:16
 */
public class LockDemo {

    static volatile Integer inventory = 100;

    public static void main(String[] args) throws InterruptedException {

        int poolSize = 4;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize, poolSize, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        long startTime = System.currentTimeMillis();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("lock1");

        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    while (inventory > 0) {
                        try {
                            lock.tryLock();

                            inventory--;
                            System.out.println(inventory);

                        } finally {
                            lock.unlock();
                        }
                    }
                }
            });
        }
        long end = System.currentTimeMillis();
        threadPoolExecutor.shutdown();
        System.out.println("执行线程数:4" + " 总耗时:" + (end - startTime) + " 库存数:" + inventory);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
