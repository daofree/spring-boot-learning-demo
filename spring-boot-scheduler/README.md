# spring-boot-scheduler
> Spring Boot 定时任务的使用

> 参考教程： http://www.ityouknow.com/springboot/2016/12/02/spring-boot-scheduler.html 

> 在开发中，有时候我们会使用定时任务来帮助我们做一些事情。而Spring Boot帮我们很好的解决了这个问题。

## Spring Boot使用定时任务

1. 引入POM依赖

```xml
<dependencies>
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    </dependency>
</dependencies>
```

2. 在启动类上使用`@EnableScheduling`注解开启定时

```java
@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSchedulerApplication.class, args);
    }

}
```

3. 创建定时任务实现类

- `PrintTask`任务类

```
@Component
public class PrintTask {

    private int count=0;

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }
}
```

- `Print2Task`任务类

```
@Component
public class Print2Task {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
```

### 参数说明

 `@Scheduled` 参数可以接受两种定时的设置，一种是我们常用的`cron="*/6 * * * * ?"`,一种是 `fixedRate = 6000`，两种都表示每隔六秒打印一下内容。 

- #### **fixedRate 说明**
  - `@Scheduled(fixedRate = 6000)` ：上一次开始执行时间点之后6秒再执行
  - `@Scheduled(fixedDelay = 6000)` ：上一次执行完毕时间点之后6秒再执行
  - `@Scheduled(initialDelay=1000, fixedRate=6000)` ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次
- #### **cron表达式**

> cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位：

* 第一位，表示秒，取值0-59
* 第二位，表示分，取值0-59
* 第三位，表示小时，取值0-23
* 第四位，日期天/日，取值1-31
* 第五位，日期月份，取值1-12
* 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
          另外：1表示星期天，2表示星期一。
* 第7为，年份，可以留空，取值1970-2099

> cron中，还有一些特殊的符号，含义如下：

- (\*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
- (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
- (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
- (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
- (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：*/y，等同于0/y

- #### 例子：

`0 0 3 * * ?`：每天3点执行

`0 5 3 * * ?` ：每天3点5分执行

`0 5 3 ? * *`：每天3点5分执行，与上面作用相同

`0 5/10 3 * * ?`：每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行

`0 10 3 ? * 1 `：每周星期天，3点10分 执行，注：1表示星期天

`0 10 3 ? * 1#3`：每个月的第三个星期，星期天 执行，#号只能出现在星期的位置