package cn.gotojava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLoggingApplication {
    /*
    日志级别：
    trace<debug<info<warn<error
        logger.trace("String");	// 跟踪日志信息
        logger.debug("String");	// 调试日志信息
        logger.info("String");  // SpringBoot默认日志级别，普通日志信息
        logger.warn("String");	// 警告日志信息
        logger.error("String");	// 错误日志信息
     */
    static final Logger logger = LoggerFactory.getLogger(SpringBootLoggingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLoggingApplication.class, args);
        new SpringBootLoggingApplication().getSay(new String[]{"张三","李四","王五"});
    }

    public void getSay(String[] name){
        for (String userName : name) {
            logger.info("Hello,"+userName+"!");
        }
    }
}