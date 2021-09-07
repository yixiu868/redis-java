package com.ww.redis.client.redisson.complex.obj;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaohua
 * @description 复杂对象使用hash格式存储，复杂对象不能快速方便的使用hash格式
 * @date 2021-9-6 23:00
 */
public class RedissonComplexObjDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new StringCodec());
        RedissonClient redisson = Redisson.create(config);
        RMap<String, Object> map = redisson.getMap("student");

        Teacher teacher = new Teacher();
        teacher.setName("张老师");
        teacher.setSubject("语文");
        teacher.setStudents(null);

        Teacher teacher2 = new Teacher();
        teacher2.setName("李老师");
        teacher2.setSubject("数学");
        teacher2.setStudents(null);

        // student按照字符串存入
        map.put("name", "詹三");
        map.put("grade", "三年级");
        map.put("teachers", Arrays.asList(teacher, teacher2));
        System.out.println("存入student成功");

        System.out.println("获取存入student信息");
        String name = (String) map.get("name");
        String grade = (String) map.get("grade");
        System.out.println("获取name:" + name + ", grade: " + grade);

        // 报错信息
        // java.lang.ClassCastException: class java.lang.String cannot be cast to class java.util.List
        List<Teacher> teachers = (List<Teacher>) map.get("teachers");
        System.out.println("获取teachers:" + teacher2);

        // 关闭redisson
        redisson.shutdown();
    }
}
