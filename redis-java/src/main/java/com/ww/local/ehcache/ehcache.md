# Ehcache

参考链接

[Ehcache详解](https://www.cnblogs.com/myseries/p/11370109.html)

## Ehcache API

### CacheManager

Cache容器对象，并管理着（添加或删除）Cache的生命周期。

```java
// 可以自己创建一个Cache对象添加到CacheManager中
public void addCache(Cache cache);
public synchronized void removeCache(String cacheName);
```

### Cache

一个Cache可以包含多个Element，并被CacheManager管理。它实现了对缓存的逻辑行为。

### Element

需要缓存的元素，它维护着一个键值对，元素可以设置有效期，0代表无限制

### 示例

```java
// 配置一个CacheManager实例
CacheManager cacheManager = CacheManager.create();
cacheManager = CacheManager.newInstance();
cacheManager = CacheManager.newInstance(".../ehcache.xml");

InputStream inputStream = new FileInputStream(new File(".../ehcache.xml"));
cacheManager = CacheManager.newInstance(inputStream);

String[] cacheNames = cacheManager.getCacheNames();
```

