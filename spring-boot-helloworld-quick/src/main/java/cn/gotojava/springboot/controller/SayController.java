package cn.gotojava.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类是一个控制类，该类的所有方法返回的数据直接打印到浏览器上
 * @RestController注解相当于@ResponseBody + @Controller
 */
@ResponseBody
@Controller
//@RestController
public class SayController {

    /**
     * 将/say的url请求映射到该方法上，并且要求请求方式为GET
     * @return 将方法返回结果直接打印在浏览器上
     */
    @RequestMapping(name = "/say", method = RequestMethod.GET)
    public String sayHello(String name){
        String username = name;
        if(username.isEmpty() || username == null) throw new RuntimeException("参数错误!");
        return "Hello,"+username;
    }
}
