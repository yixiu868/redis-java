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

	@Test public void testSpringRedis() { // stringRedisTemplate�Ĳ��� // String��д
	  redisTemplate.delete("myStr"); redisTemplate.opsForValue().set("myStr",
	  "skyLine"); System.out.println(redisTemplate.opsForValue().get("myStr"));
	  System.out.println("---------------");
	  
	  // List��д redisTemplate.delete("myList");
	  redisTemplate.opsForList().rightPush("myList", "T");
	  redisTemplate.opsForList().rightPush("myList", "L");
	  redisTemplate.opsForList().leftPush("myList", "A"); List<String> listCache =
	  redisTemplate.opsForList().range("myList", 0, -1); for (String s : listCache)
	  { System.out.println(s); } System.out.println("---------------");
	  
	  // Set��д redisTemplate.delete("mySet");
	  redisTemplate.opsForSet().add("mySet", "A");
	  redisTemplate.opsForSet().add("mySet", "B");
	  redisTemplate.opsForSet().add("mySet", "C"); Set<String> setCache =
	  redisTemplate.opsForSet().members("mySet"); for (String s : setCache) {
	  System.out.println(s); } System.out.println("---------------");
	  
	  // Hash��д redisTemplate.delete("myHash");
	  redisTemplate.opsForHash().put("myHash", "BJ", "����");
	  redisTemplate.opsForHash().put("myHash", "SH", "�Ϻ�");
	  redisTemplate.opsForHash().put("myHash", "HN", "����"); Map<String, String>
	  hashCache = redisTemplate.opsForHash().entries("myHash"); for (Map.Entry
	  entry : hashCache.entrySet()) { System.out.println(entry.getKey() + " - " +
	  entry.getValue()); } System.out.println("---------------"); 
	  
	  // ������redisUtil����
	  redisUtil.set("dream", "I have a dream");
	  System.out.println("ȡ��keyΪdream��ֵ��" + redisUtil.get("dream"));
	}
	 
	
	/*
	 * @Test public void test() { if (null == redisUtil) {
	 * System.out.println("redisUtil����ע��ʧ��"); } else {
	 * System.out.println("redisUtil����ע��ɹ�"); } }
	 */
}
