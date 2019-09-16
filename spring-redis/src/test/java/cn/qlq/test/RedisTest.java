package cn.qlq.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.qlq.util.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
//@ContextConfiguration("classpath:config.xml")
@SuppressWarnings("all")
public class RedisTest {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired 
	private RedisTemplate redisTemplate;	

	@Test public void testSpringRedis() { // stringRedisTemplate的操作 // String读写
	  redisTemplate.delete("myStr"); redisTemplate.opsForValue().set("myStr",
	  "skyLine"); System.out.println(redisTemplate.opsForValue().get("myStr"));
	  System.out.println("---------------");
	  
	  // List读写 redisTemplate.delete("myList");
	  redisTemplate.opsForList().rightPush("myList", "T");
	  redisTemplate.opsForList().rightPush("myList", "L");
	  redisTemplate.opsForList().leftPush("myList", "A"); List<String> listCache =
	  redisTemplate.opsForList().range("myList", 0, -1); for (String s : listCache)
	  { System.out.println(s); } System.out.println("---------------");
	  
	  // Set读写 redisTemplate.delete("mySet");
	  redisTemplate.opsForSet().add("mySet", "A");
	  redisTemplate.opsForSet().add("mySet", "B");
	  redisTemplate.opsForSet().add("mySet", "C"); Set<String> setCache =
	  redisTemplate.opsForSet().members("mySet"); for (String s : setCache) {
	  System.out.println(s); } System.out.println("---------------");
	  
	  // Hash读写 redisTemplate.delete("myHash");
	  redisTemplate.opsForHash().put("myHash", "BJ", "北京");
	  redisTemplate.opsForHash().put("myHash", "SH", "上海");
	  redisTemplate.opsForHash().put("myHash", "HN", "河南"); Map<String, String>
	  hashCache = redisTemplate.opsForHash().entries("myHash"); for (Map.Entry
	  entry : hashCache.entrySet()) { System.out.println(entry.getKey() + " - " +
	  entry.getValue()); } System.out.println("---------------"); 
	  
	  // 工具类redisUtil操作
	  redisUtil.set("dream", "I have a dream");
	  System.out.println("取出key为dream的值：" + redisUtil.get("dream"));
	}
	 
	
	/*
	 * @Test public void test() { if (null == redisUtil) {
	 * System.out.println("redisUtil依赖注入失败"); } else {
	 * System.out.println("redisUtil依赖注入成功"); } }
	 */
}
