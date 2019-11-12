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
}
