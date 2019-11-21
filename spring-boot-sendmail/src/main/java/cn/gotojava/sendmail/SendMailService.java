package cn.gotojava.sendmail;

public interface SendMailService {
    void sendSimpleMail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject);
}
