package cn.gotojava.springboot;

import cn.gotojava.springboot.bean.PersonAnnotationProperties;
import cn.gotojava.springboot.bean.PersonProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Boot单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonAnnotationPropertiesAppTest {

    // 注入Person对象
    @Autowired
    private PersonAnnotationProperties personAnnotationProperties;

    @Test
    public void contextLoads() {
        System.out.println(personAnnotationProperties);
    }

}
