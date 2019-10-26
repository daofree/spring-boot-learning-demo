# spring-boot-log #
> Spring Boot Logback日志框架的使用
>
> 需求：通过LoggerFactory记录日志信息

## 什么是日志？ ##
> 日志文件是用于**记录系统操作事件**的记录文件或文件集合，可分为事件日志和消息日志。具
> 有`处理历史数据`、`诊断问题`的追踪以及理解系统的活动等重要作用。
> ---来源自《百度百科》

- Java主流日志工具介绍和使用：[https://blog.csdn.net/weixin_40581980/article/details/80986177](https://blog.csdn.net/weixin_40581980/article/details/80986177 "https://blog.csdn.net/weixin_40581980/article/details/80986177")

## SLF4j的使用 ##
+ SLF4j
	- 官方文档：[http://www.slf4j.org/docs.html](http://www.slf4j.org/docs.html "http://www.slf4j.org/docs.html")
	- 用户手册：[http://www.slf4j.org/manual.html](http://www.slf4j.org/manual.html "http://www.slf4j.org/manual.html")

- ### SLF4j依赖结构： ###
![SLF4j](http://www.slf4j.org/images/concrete-bindings.png)


![slf4j](http://www.slf4j.org/images/legacy.png)

- ### SLF4J输出`hello world` ###

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
  	// 获取LoggerFactory对象
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```
- ### 日志级别 ###
`trace`<`debug`<`info`<`warn`<`error`
```java
logger.trace("String");	// 跟踪日志信息
logger.debug("String");	// 调试日志信息
logger.info("String");  // SpringBoot默认日志级别，普通日志信息
logger.warn("String");	// 警告日志信息
logger.error("String");	// 错误日志信息
```

请参考日志规范：[ https://www.cnblogs.com/kofxxf/p/3713472.html ]( https://www.cnblogs.com/kofxxf/p/3713472.html )

- ### 日志使用示例

```java
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
```

- 效果

![logbakUse](..\images\logbackUse.png)
