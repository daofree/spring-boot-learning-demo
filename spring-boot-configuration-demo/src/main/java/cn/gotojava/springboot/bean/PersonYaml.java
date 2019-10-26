package cn.gotojava.springboot.bean;

import cn.gotojava.springboot.util.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将application.yml文件中的配置注入到该类对象属性中
 *
 * @ConfigurationProperties告知Spring Boot将本类中的对象属性与配置文件中的配置信息进行绑定
 *  prefix = "person"： 配置文件中哪个下面的所有属性进行映射绑定
 *  @PropertySource value = "classpath:person.yml"：指定配置文件位置
 *  ignoreResourceNotFound：指定的配置文件不存在是否报错，默认是false。当设置为 true 时，若该文件不存在，程序不会报错。
 *  encoding：加载配置文件的编码
 *  factory：解析资源文件的工厂类，默认实现是<strong>DefaultPropertySourceFactory</strong>
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value = {"classpath:person.yml"}, factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = false, encoding = "utf-8")
public class PersonYaml implements Serializable {
    private String name;    // 姓名
    private Integer age;    // 年龄
    private Boolean sex;    // 性别(true男/false女)
    private Date birthday;  // 生日
    private Map<String, Object> maps;   // map
    private List<Object> lists; // lists
    private Dog dog;    // dog
}
