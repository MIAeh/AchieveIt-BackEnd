package com.achieveit.application.utils;

import com.achieveit.application.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailUtil {

    @Value("${spring.mail.username}")
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    private final Logger logger = LoggerFactory.getLogger(EmailUtil.class);


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
    @Async("taskExecutor")
    public void sendTextEmail(String to, String subject, String content) {
        if (to != null && !to.isEmpty() && subject != null && !subject.isEmpty() && content != null && !content.isEmpty()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setReplyTo(to);
            message.setSubject(subject);
            message.setText(content);
            message.setSentDate(new Date());
            logger.info("Mail Sending: " + message.toString());
            sender.send(message);
            logger.info("Mail Sent");
        }

    }

}