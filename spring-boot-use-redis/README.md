# spring-boot-use-redis

>  [Spring Boot 中 Redis 的使用](http://www.ityouknow.com/springboot/2016/03/06/spring-boot-redis.html) 

文档请参考： http://www.ityouknow.com/springboot/2016/03/06/spring-boot-redis.html

## 使用步骤

1. 引入maven依赖

```tex
<!--Spring Boot redis依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!--Spring Boot Redis数据库对象连接池-->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

2. 编写`application.properties`/`application.yml`配置文件

```yaml
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=www.gotojava.cn
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制） 默认 8
spring.redis.lettuce.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
spring.redis.lettuce.pool.max-wait=-1
# 连接池中的最大空闲连接 默认 8
spring.redis.lettuce.pool.max-idle=8
# 连接池中的最小空闲连接 默认 0
spring.redis.lettuce.pool.min-idle=0
```

3. 编写缓存配置类(RedisConfig)

```java
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration  // 声明该类为配置类
@EnableCaching  // 开启缓存
public class RedisConfig extends CachingConfigurerSupport {

    // 注册bean
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
```

4. 使用SpringBoot 单元测试测试Redis

```java
package cn.gotojava.config;

import cn.gotojava.vo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        Logger logger = LoggerFactory.getLogger(TestRedis.class);
        User user=new User("aa@126.com", "aa", "aa123456", "aa123");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox",user);
        operations.set("com.neo.info",user,1,TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("com.neox");
        Boolean aBoolean = redisTemplate.hasKey("com.neo.info");
        if(exists){
            User userObj = operations.get("com.neox");
            logger.info("User:{}",userObj.toString());
        }
        if (aBoolean){
            User userObject = operations.get("com.neo.info");
            logger.info("User:{}",userObject.toString());
        }

    }
}
```

##  RedisTemplate常用方法总结

| 方法名                                              | 描述                                                   |
| --------------------------------------------------- | ------------------------------------------------------ |
| hasKey(key)                                         | 判断是否有key所对应的值，有则返回true，没有则返回false |
| opsForValue().get(key)                              | 有则取出key值所对应的值                                |
| delete(key)                                         | 删除单个key值                                          |
| delete(keys)                                        | 批量删除key                                            |
| dump(key)                                           | 将当前传入的key值序列化为byte[]类型                    |
| expire(String key, long timeout, TimeUnit unit)     | 设置过期时间                                           |
| getPatternKey(String pattern)                       | 查找匹配的key值，返回一个Set集合类型                   |
| renameKey(String oldKey, String newKey)             | 修改redis中key的名称                                   |
| getKeyType(String key)                              | 返回传入key所存储的值的类型                            |
| renameOldKeyIfAbsent(String oldKey, String newKey)  | 如果旧值存在时，将旧值改为新值                         |
| randomKey()                                         | 从redis中随机取出一个key                               |
| getExpire(String key)                               | 返回当前key所对应的剩余过期时间                        |
| getExpire(String key, TimeUnit unit)                | 返回剩余过期时间并且指定时间单位                       |
| persistKey(String key)                              | 将key持久化保存                                        |
| moveToDbIndex(String key, int dbIndex)              | 将当前数据库的key移动到指定redis中数据库当中           |
| opsForValue().set(key, value)                       | 设置当前的key以及value值                               |
| opsForValue().set(key, value, timeout, unit)        | 设置当前的key以及value值并且设置过期时间               |
| getCharacterRange(String key, long start, long end) | 返回key中字符串的子字符                                |
| setKeyAsValue(String key, String value)             | 将旧的key设置为value，并且返回旧的key                  |
| multiGet(Collection<String> keys)                   | 批量获取值                                             |
| opsForValue().append(key, value)                    | 在原有的值基础上新增字符串到末尾                       |
| opsForValue().multiSetIfAbsent(valueMap)            | 如果对应的map集合名称不存在，则添加否则不做修改        |
| opsForValue().multiSet(valueMap)                    | 设置map集合到redis                                     |
| opsForValue().size(key)                             | 获取字符串的长度                                       |
| opsForValue().setIfAbsent(key, value)               | 重新设置key对应的值，如果存在返回false，否则返回true   |
| opsForValue().set(key, value, timeout, unit)        | 将值 value 关联到 key,并将 key 的过期时间设为 timeout  |
| opsForValue().getBit(key, offset)                   | 对key所储存的字符串值，获取指定偏移量上的位(bit)       |
| hGetAll(String key)                                 | 获取变量中的键值对                                     |
| hashExists(String key, String field)                | 查看hash表中指定字段是否存在                           |
| opsForList().index(key, index)                      | 通过索引获取列表中的元素                               |

> 更多请参考： https://blog.csdn.net/sinat_22797429/article/details/89196933 