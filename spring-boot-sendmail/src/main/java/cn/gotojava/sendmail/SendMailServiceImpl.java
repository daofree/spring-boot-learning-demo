package cn.gotojava.sendmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SendMailServiceImpl implements SendMailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入邮件发送对象
    @Autowired
    private JavaMailSender mailSender;

    //发件人
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送简单邮件
     * @param to    收件人
     * @param subject   邮件主题
     * @param content   邮件主体内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！{}", e);
        }

    }

    /**
     * 发送HTML邮件
     * @param to    收件人
     * @param subject   邮件主题
     * @param content   邮件主体内容
     */
    @Override
    public void sendHtmlMail(String to, String subject) {
        // 定义html内容
        String content = "";
        this.sendSimpleMail(to,subject,content);
    }


}
