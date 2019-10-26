package cn.gotojava.springboot;

import cn.gotojava.springboot.bean.PersonYaml;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Boot单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonYamlAppTest {

    // 将person.yml的info对象信息绑定到该对象属性上
    @Value("${person.info}")
    private String personInfo;

    // 绑定person.yaml文件配置的random对象
    @Value("${person.randomValue}")
    private String randomValue;
    @Value("${person.randomInt}")
    private String randomInt;
    @Value("${person.randomLong}")
    private String randomLong;
    @Value("${person.randomIntRange}")
    private String randomIntRange;
    @Value("${person.randomUUID}")
    private String randomUUID;
    @Value("${person.randomIntArrayRange}")
    private String randomIntArrayRange;

    // 注入Person对象
    @Autowired
    private PersonYaml personYaml;

    @Test
    public void contextLoads() {
        // 打印信息
        System.out.println(personInfo);
        // 打印personYaml对象的信息
        System.err.println(personYaml);
    }

    /**
     * 获取person.yaml文件配置的随机数信息
     */
    @Test
    public void getRandomInfo(){
        System.err.println("RANDOM INFO：(" +
                "randomValue="+randomValue  +
                ",randomInt="+randomInt     +
                ",randomLong="+randomLong    +
                ",randomIntRange="+randomIntRange+
                ",randomUUID="+randomUUID   +
                ",randomIntArrayRange="+randomIntArrayRange +
                ")");
    }

}