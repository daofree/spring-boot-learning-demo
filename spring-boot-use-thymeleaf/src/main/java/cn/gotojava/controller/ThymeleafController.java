package cn.gotojava.controller;

import cn.gotojava.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
