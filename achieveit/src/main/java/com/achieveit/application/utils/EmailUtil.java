package com.achieveit.application.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Component
public class EmailUtil {

    @Value("${spring.mail.username}")
    String from;

    private final JavaMailSender sender;

    public EmailUtil(JavaMailSender sender) {
        this.sender = sender;
    }

    /**
     * 发送一般文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendTextEmail(String to, String subject, String content){
//        if (to != null && !to.isEmpty() && subject != null && !subject.isEmpty() && content != null && !content.isEmpty()) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(from);
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(content);
//            message.setSentDate(new Date());
//            sender.send(message);
//        }
    }

}