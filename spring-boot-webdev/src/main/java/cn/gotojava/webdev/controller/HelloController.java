package cn.gotojava.webdev.controller;

import cn.gotojava.webdev.config.FileConfiguration;
import cn.gotojava.webdev.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    static final Logger log = LoggerFactory.getLogger(HelloController.class);

    // 自动注入文件配置类
    @Autowired
    private FileConfiguration file;

    @RequestMapping(value = "/hello")
    public String index(){
        return "Hello World";
    }

    @RequestMapping(value = "/getUser")
    public User getUser(){
        User user = new User();
        user.setUserName("张三");
        user.setUserSex("男");
        user.setUserAge(20);
        user.setUserAddress("中国上海");
        log.info(user.toString());
        return user;
    }

    @RequestMapping(value = "/getFileConfInfo")
    public String getFileConfigInfo(){
        String title = file.getTitle();
        String description = file.getDescription();
        log.info("================文件配置已获取================");
        return "标题："+title+"\t说明："+description;
    }
}
