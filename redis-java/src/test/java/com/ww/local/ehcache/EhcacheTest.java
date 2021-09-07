package com.ww.local.ehcache;

import org.junit.Test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {

	@Test
	public void test01() {
		// 1-创建缓存管理器
		CacheManager cacheManager = CacheManager.create("ehcache.xml");
		
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
		
		// 7-刷新缓存到磁盘
		cache.flush();
		
		// 8-关闭缓存管理器
		cacheManager.shutdown();
	}
	
	@Test
	public void test02() {
		System.out.println("hello world");
	}
}
