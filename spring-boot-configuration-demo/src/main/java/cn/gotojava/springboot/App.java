package cn.gotojava.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
读取外部Spring配置文件,让配置文件中的内容生效
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
