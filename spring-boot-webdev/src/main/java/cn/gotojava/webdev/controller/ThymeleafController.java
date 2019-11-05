package cn.gotojava.webdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

    @RequestMapping(value = "/view")
    public ModelAndView returnView(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","Thymeleaf 是一款用于渲染 XML/XHTML/HTML5 内容的模板引擎。");
        mav.setViewName("/index");
        System.out.println("执行了");
        return mav;
    }
}
