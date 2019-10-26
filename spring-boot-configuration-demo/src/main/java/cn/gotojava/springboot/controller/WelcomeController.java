package cn.gotojava.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping(name = "/")
    public String index(){
        return "你好，世界";
    }
}
