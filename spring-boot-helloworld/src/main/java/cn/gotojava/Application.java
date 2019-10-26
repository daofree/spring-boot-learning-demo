package cn.gotojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication  来标记一个主程序类，说明这是一个Spring Boot的应用
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring Boot应用启动方法
        SpringApplication.run(Application.class,args);
    }
}
