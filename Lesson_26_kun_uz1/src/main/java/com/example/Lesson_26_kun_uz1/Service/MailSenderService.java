package com.example.Lesson_26_kun_uz1.Service;




import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromAccount;

    public void sendEmail(String toAccount, String subject, String text) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(toAccount);
//        msg.setSubject(subject);
//        msg.setText(text);
//        msg.setFrom(fromAccount);
//        javaMailSender.send(msg);
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}





