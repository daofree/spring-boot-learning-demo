# spring-boot-use-jpa

> Spring Boot 使用JPA配合Thymeleaf实现`增`、`删`、`改`、`查`。

文档参考： http://www.ityouknow.com/springboot/2016/08/20/spring-boot-jpa.html

## 1.JPA是什么?

JPA(Java Persistence API)的缩写，是Sun官方提出的**Java持久化规范**， 它为 Java 开发人员提供了一种对象/关联映射工具来管理 Java 应用中的关系数据。目的在于**简化现有JavaEE应用开发工作和整合ORM技术**，实现天下归一的局面。这里需要注意的是***JPA是一套规范，不是一个产品***。

## 2.Spring Boot JPA又是啥？

 Spring Boot JPA 是 Spring **基于 ORM 框架、JPA 规范的基础上封装的一套 JPA 应用框架**，可使开发者*用极简的代码即可实现对数据的访问和操作*。它提供了包括增删改查等在内的常用功能，且易于扩展！ 

## 3.Spring Boot JPA的使用

1. 导入相关依赖

```yaml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.22.RELEASE</version>
        <relativePath/>
    </parent>
    <groupId>cn.gotojava</groupId>
    <artifactId>spring-boot-use-jpa</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-use-jpa</name>
    <description>Spring Boot 使用 JPA</description>

    <properties>
        <java.version>1.8</java.version>

        <!-- 修改thymeleaf版本 -->
        <thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
        <thymeleaf-layout-dialect.version>2.0.4</thymeleaf-layout-dialect.version>
    </properties>

    <dependencies>
        <!--Spring Boot启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--Spring Boot Web启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--Spring Boot热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!--提供对象的getter/setter/toString等方法-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--引入Thymeleaf依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!--Spring Boot Data jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!--MySQL中间件-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!--Spring Boot 单元测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

2. 编写`application.yml`配置

```yaml
logging:
  level: debug
spring:
  datasource:
    ########################### MySQL数据源配置 ##################
    # MySQL连接驱动
    driver-class-name: com.mysql.jdbc.Driver
    # MySQL地址
    url: jdbc:mysql://www.gotojava.cn:3306/spring-boot-jpa-demo
    # MySQL连接用户名
    username: root
    # MySQL连接密码
    password: mysql926917
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL5Dialect
    hibernate:
      ddl-auto: update
```

3. 编写User实体类

```java
@Data
@Entity
@Table(name = "s_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;    // 用户姓名

    @Column(name = "user_age")
    private String userAge;     // 用户年龄

    @Column(name = "user_address")
    private String userAddress; // 用户地址

    @Column(name = "user_email")
    private String userEmail;   // 用户email

    @Column(name = "user_sex")
    private Integer userSex;    // 用户性别

    @Column(name = "user_phone")
    private String userPhone;   // 用户手机
}

```

## 4.编写基础查询

```java
/**
 * 功能：对User的增、删、改、查
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    
}
```

> 这里的UserRepository接口为什么什么都没有呢？这就是Spring Data Jpa的强大之处，它所提供的接口中，已帮我们提供了很多的常用方法，基本完全可以满足日常的CRUD(CREATE、READ、UPDATE、DELETE)的需求。

- JPA接口的继承关系

![]( http://cloudstorage.gotojava.cn/jpaInterface.png)

- `JpaRepository`： Spring Data 里面做数据库操作的最底层的抽象接口，最顶级的父类，源码里面其实什么方法都没有，仅仅起到一个标识作用。管理域类以及域类的 ID 类型作为类型参数，此接口主要作为标记接口来捕获要使用的类型，并帮助用户发现扩展此接口的接口。

- `PagingAndSortingRepository`： 扩展[`CrudRepository`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)提供了使用分页和排序抽象来检索实体的其他方法。 

- `QueryByExampleExecutor`： 允许通过示例[`Example`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Example.html)实例执行查询的接口。 

- `CrudRepository`： 特定类型的存储库上的通用CRUD操作的接口。 

- `NoRepositoryBean`：注释，用于排除存储库接口，从而导致创建实例。当为所有存储库提供扩展的基础接口，并结合自定义存储库基类以实现在该中间接口中声明的方法时，通常会使用此方法。在这种情况下，您通常会从中间接口派生出具体的存储库接口，但又不想为中间接口创建Spring bean。

- `Repository`：中央存储库标记界面。捕获要管理的域类型以及域类型的ID类型。通用目的是保存类型信息以及能够在类路径扫描过程中发现扩展此信息的接口，以轻松创建Spring bean。

  扩展此接口的域存储库可以通过简单地声明与中声明的签名相同的方法来选择性地公开CRUD方法[`CrudRepository`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)。