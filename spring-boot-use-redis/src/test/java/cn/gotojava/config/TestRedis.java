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
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    // StringRedisTemplate继承与RedisTemplate，但二者数据保存在各自的空间里，数据不互通

    /*
    StringRedisTemplate采用String的序列化策略
        存储和读取，都为可读的数据。
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
    RedisTemplate采用JDK的序列化策略
        存储时，先将数据序列化为字节数组，再存入Redis数据库。查看Redis会发现，是字节数组的形式。
     */
    @Autowired
    private RedisTemplate redisTemplate;

    static final Logger logger = LoggerFactory.getLogger(TestRedis.class);

    /**
     * StringRedisTemplate测试，redis数据库内容可见
     */
    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa", "111");
        logger.info("111 == 111：{}","111".equals(stringRedisTemplate.opsForValue().get("aaa")));
    }

    /**
     * RedisTemplate测试，数据的Key/Value都为字节数据
     * @throws Exception
     */
    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456", "aa123");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
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

    /**
     * 判断指定Key值是否存在
     * @return 存在返回true，不存在返回false
     */
    @Test
    public void keyExists(){
//        Boolean user = redisTemplate.hasKey("user");  // false
        Boolean user = redisTemplate.hasKey("com.neox");    // true
        logger.info("UserKey：{}",user);
    }

    /**
     * 获取指定key的value值
     * @return 返回指定key的value对象
     */
    @Test
    public void getKey(){
        Object value = redisTemplate.opsForValue().get("com.neox");
        logger.info("UserValue：{}",value);
    }

    /**
     * 删除指定key
     * @return 删除成功返回true,删除失败返回false
     */
    @Test
    public void deleteKey(){
        Boolean neox = redisTemplate.delete("com.neox");
        logger.info("删除状态：{}",neox);
    }

    /**
     * 批量删除Key
     * @return 返回删除成功的记录条数
     */
    @Test
    public void batchDeleteKey(){
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        Long delete = redisTemplate.delete(list);
        logger.info("批量删除记录：{}",delete);
    }

    /**
     * 设置键(key)和值(value)
     */
    @Test
    public void setKeyAndValue(){
        redisTemplate.opsForValue().set("zhangsan", "张三");
        redisTemplate.opsForValue().set("lisi", "李四");
        redisTemplate.opsForValue().set("wangwu", "王五");
        stringRedisTemplate.opsForValue().set("zhangsan", "张三");
        stringRedisTemplate.opsForValue().set("lisi", "李四");
        stringRedisTemplate.opsForValue().set("wangwu", "王五");
    }

    /**
     * 设置键(key)的同时，设置该键的过期时间
     * expire(键,时间,时间单位);
     * - TimeUnit.SECONDS   秒
     * - TimeUnit.DAYS      天
     * - TimeUnit.HOURS     小时
     * - TimeUnit.MINUTES   分钟
     * - TimeUnit.MICROSECONDS  微秒
     * - TimeUnit.NANOSECONDS   纳米秒
     */
    @Test
    public void setKeyAndValueAndExpireDate(){
        Boolean aaa = redisTemplate.expire("aaa", 30, TimeUnit.SECONDS);
        logger.info("设置时间状态：{}",aaa);
        Boolean aaa1 = stringRedisTemplate.expire("aaa", 30, TimeUnit.SECONDS);
        logger.info("设置时间状态：{}",aaa1);
    }

    /**
     * 查找所有符合给定模式( pattern)的 key
     * @return 返回一个Set的集合
     */
    @Test
    public void getPatternKey(){
        Set<String> keys = stringRedisTemplate.keys("*");
        keys.forEach((obj)->{
            logger.info("value：{}",obj);
        });
    }

}
