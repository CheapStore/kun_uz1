package com.example.Lesson_26_kun_uz1;




import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailing {
    String email = "allayarovshahzodbekz@gmail.com";
    String password = "kqvmpnebrzwmqowa";

    public void sendMail(String receiver, String message) throws MessagingException {
        Session session = getSession(getProperties(), email, password);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(email);
        mimeMessage.setRecipients(Message.RecipientType.TO, receiver);
        mimeMessage.setContent(message, "text/plain");
        mimeMessage.setSubject("codeuz");
        Transport.send(mimeMessage);
    }

    public Session getSession(Properties properties, String email, String password) {
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }
}


