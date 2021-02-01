package com.devilray.webutils;

import com.devilray.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Component
@Transactional
public class EmailManager {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private AccountsRepository userRepository;

    public void sendMail(String to, String msg, String subject) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setText(msg);
        helper.setSubject(subject);
        sender.send(message);

    }

}
