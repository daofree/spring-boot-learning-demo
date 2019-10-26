package cn.gotojava.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    ApplicationContext appContext;

    @Test
    public void testBeanService(){
        boolean beanService = appContext.containsBean("beanService");
        System.out.println("is exist BeanService:"+beanService);
    }
}
