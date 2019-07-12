package com.cron.service.controller;

import com.cron.service.entity.FormSendMail;
import com.cron.service.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@Scope("request")
public class SendMail {
    @Autowired
    SongRepository songRepository;

    @PostMapping("/send")
    public String sendMail(
            @RequestBody FormSendMail formSendMail
    ) {

        // Recipient's email ID needs to be mentioned.
        String to = formSendMail.getEmailTo();

        // Sender's email ID needs to be mentioned
        String from = MailConfig.APP_EMAIL;
        final String username = MailConfig.APP_EMAIL;//change accordingly
        final String password = MailConfig.APP_PASSWORD;//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Did you get my message?");

            // Now set the actual message

            message.setText("http://localhost:8090/gift?songid=" + formSendMail.getSongId() + "&note=" + formSendMail.getNote());

            // Send message
            Transport.send(message);



        } catch (MessagingException e) {
            e.printStackTrace();
            return "Sent message fail....";
        }
        return "Sent message successfully....";
    }
}

