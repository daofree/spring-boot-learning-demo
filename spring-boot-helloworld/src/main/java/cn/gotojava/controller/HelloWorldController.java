package cn.gotojava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController
    标记该类是一个Controller类，类中所有的方法的返回值全部以json字符串返回
 */
@RestController
public class HelloWorldController {

    /**
     * 将请求路径为/hello的请求映射到该方法上
     * @return 返回一个hello world的字符串
     */
    @RequestMapping(name = "/hello")
    public String hello(){
        return "hello world,你好世界!!";
    }
}
