package com.ww.redis.client.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaohua
 * @version 1.0
 * @description // TODO
 * @date 2021-7-8 0:01
 */
@RestController
@RequestMapping("/redisson")
public class RedissonTestController {

    @Autowired
    private RedissonClient redisson;


    public String getWatchDog() throws InterruptedException {
        RLock watchDogLock = redisson.getLock("watchDogLock");
        boolean judge;

        // 进行3秒的尝试时间,如果失败则返回false
        judge = watchDogLock.tryLock(3, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName() + "的锁获取情况:" + judge);

        if (judge) {
            try {
                System.out.println(Thread.currentThread().getName() + "已经成功获取到的分布式锁");
                // 执行主流程业务
                TimeUnit.SECONDS.sleep(12);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "进行解锁操作");
                watchDogLock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "未获取到锁");
        }

        // 最后输出
        if (judge) {
            return "成功获取分布式锁";
        } else {
            return "获取分布式锁失败";
        }
    }
}
