package cn.gotojava.sendmail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImpl {

    @Autowired
    private SendMailService sendMailService;

    /**
     * 发送普通邮件
     */
    @Test
    public void sendMailTest(){
        // 发送简单邮件
        sendMailService.sendSimpleMail("353654768@qq.com","Hello Say","这是一份Spring Boot发出的测试邮件");


    }

    /**
     * 发送带HTML的邮件
     */
    @Test
    public void sendHtmlMailTest() {
        // 发送HTML邮件
        sendMailService.sendHtmlMail("2638385556@qq.com","HTML邮件内容测试");
    }
}
