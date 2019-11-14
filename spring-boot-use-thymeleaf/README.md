

# spring-boot-use-thymeleaf

> Spirng Boot 使用Thymeleaf

文档参考：[Thymeleaf使用详解-纯洁的微笑](http://www.ityouknow.com/springboot/2016/05/01/spring-boot-thymeleaf.html)

Thymeleaf官方指南：https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#integrating-thymeleaf-with-spring 

## Thymeleaf介绍

简单说，Thymeleaf 是一个跟 Velocity、FreeMarker 类似的模板引擎，它可以完全替代 JSP 。相较与其他的模板引擎，它有如下三个极吸引人的特点：

- 1.Thymeleaf 在有网络和无网络的环境下皆可运行，即它可以让美工在浏览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果。这是由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到模板+数据的展示方式。浏览器解释 html 时会忽略未定义的标签属性，所以 Thymeleaf 的模板可以静态地运行；当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示。
- 2.Thymeleaf 开箱即用的特性。它提供标准和 Spring 标准两种方言，可以直接套用模板实现 JSTL、 OGNL表达式效果，避免每天套模板、改 Jstl、改标签的困扰。同时开发人员也可以扩展和创建自定义的方言。
- 3.Thymeleaf 提供 Spring 标准方言和一个与 SpringMVC 完美集成的可选模块，可以快速的实现表单绑定、属性编辑器、国际化等功能。

## Thymeleaf的使用步骤

1. 引用Thymeleaf依赖

```java
<!--引入Thymeleaf依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

2. 编写控制器`ThymeleafController`

```java
@Controller
public class ThymeleafController {

    /**
     * Thymeleaf显示属性
     * @param model
     * @return  show.html
     */
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(Model model){
        model.addAttribute("uid","123456789");
        model.addAttribute("name","Charles");
        return "show";
    }

    /**
     * Thymeleaf显示集合
     * @param model
     * @return  show.html
     */
    @RequestMapping(value = "showlist",method = RequestMethod.GET)
    public String showList(Model model){

        List<User> userslist = new ArrayList<>();

        // user1
        User user1 = new User();
        user1.setUsername("张三");
        user1.setAge("18");
        user1.setEmail("123456@qq.com");
        user1.setAddress("中国上海");
        userslist.add(user1);

        // user2
        User user2 = new User();
        user2.setUsername("李四");
        user2.setAge("20");
        user2.setEmail("456789@qq.com");
        user2.setAddress("中国上海");
        userslist.add(user2);

        // user3
        User user3 = new User();
        user3.setUsername("王五");
        user3.setAge("22");
        user3.setEmail("234678@qq.com");
        user3.setAddress("中国上海");
        userslist.add(user3);

        model.addAttribute("userlist",userslist);

        return "show";
    }
}
```

3. Thymeleaf页面显示`show.html`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>SpringBoot模版渲染</title>
    <style type="text/css">
        #list-text td{text-align: center;}
    </style>
</head>
<body>
<p th:unless="${uid} == null" th:text="'用户ID：'+${uid}"></p>
<p th:if="${name} != null" th:text="'用户姓名：'+${name}"></p>

<table th:if="${userlist} != null">
    <tr><th th:colspan="4">Thymeleaf处理List集合</th></tr>
    <tr id="list-text"><td clas>用户名</td><td>地址</td><td>年龄</td><td>EMAIL</td></tr>
    <tr th:each="list:${userlist}">
        <td th:text="*{list.username}"></td>
        <td th:text="*{list.address}"></td>
        <td th:text="*{list.age}"></td>
        <td th:text="*{list.email}"></td>
    </tr>
</table>

<a th:href="@{https://www.google.com.hk}">Click Google</a>
</body>
<script th:inline="javascript" type="text/javascript">
    alert("Thymeleaf模版渲染");
</script>
</html>
```

## 页面效果

- Thymeleaf显示属性：

![]( http://cloudstorage.gotojava.cn/thymeleaf-showproperty.png )

- Thymeleaf显示list集合

![]( http://cloudstorage.gotojava.cn/thymeleaf-showlist.png )

## 问题整理

> org.xml.sax.SAXParseException: 元素类型 "meta" 必须由匹配的结束标记 "</meta>" 终止。

解决方案：

pom.xml文件修改Thymeleaf版本信息

```tex
<properties>
    <!-- 修改JDK的编译版本为1.8 -->
    <java.version>1.8</java.version>

    <!-- 修改thymeleaf版本 -->
    <thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
    <thymeleaf-layout-dialect.version>2.0.4</thymeleaf-layout-dialect.version>
</properties>
```

参考： https://www.jianshu.com/p/a550014d85ad 

> IDEA中如何使用debug调试项目 一步一步详细教程

参考： https://blog.csdn.net/yxl_1207/article/details/80973622 