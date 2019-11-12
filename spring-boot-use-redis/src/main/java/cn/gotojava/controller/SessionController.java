package cn.gotojava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class SessionController {

    @ResponseBody
    @RequestMapping("/uid")
    public String uid(HttpSession session){
        // 获取uid的session对象，转换为UUID
        UUID uid = (UUID) session.getAttribute("uid");
        // 判断uid是否为空
        if (uid == null){
            // 生成随机UUID
             uid = UUID.randomUUID();
        }

        Logger logger = LoggerFactory.getLogger(SessionController.class);
        logger.info("SESSION_ID："+uid);
        // 将UUID设置到session会话中
        session.setAttribute("uid",uid);
        return session.getId();
    }
}
