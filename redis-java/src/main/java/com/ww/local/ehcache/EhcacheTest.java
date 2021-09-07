package com.ww.local.ehcache;

import java.net.URL;
import java.util.Objects;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {

	public static void main(String[] args) {
		CacheManager cacheManager = null;
		
    	try { 
    		// 获取classpath根目录
    		URL url = EhcacheTest.class.getResource("/");
    		
			// 1-创建缓存管理器
			cacheManager = CacheManager.create(url.getPath() + "ehcache.xml");
			
			// 2-获取缓存对象
			Cache cache = cacheManager.getCache("helloworldCache");
			
			// 3-创建元素
			Element element = new Element("key1", "value1");
			
			// 4-将元素添加到缓存
			cache.put(element);
			
			// 5-获取缓存
			Element value = cache.get("key1");
			System.out.println("value: " + value);
			System.out.println(value.getObjectValue());
			
			// 6-删除元素
			cache.remove("key1");
			
			// 存储对象
			Dog dog = new Dog("xiaodog", "white", 2);
			Element element2 = new Element("dog", dog);
			cache.put(element2);
			Element value2 = cache.get("dog");
			System.out.println("获取value2: " + value2);
			Dog dog2 = (Dog) value2.getObjectValue();
			System.out.println("打印缓存dog信息: " + dog2);
			
			System.out.println("获取缓存元素数量: " + cache.getSize());
			
			// 7-刷新缓存到磁盘
			cache.flush();
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(cacheManager)) {
				// 8-关闭缓存管理器
				cacheManager.shutdown();
			}
		}
	}
}
