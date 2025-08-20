package br.com.oldschool69.rest_with_spring_boot_and_java.services;

import br.com.oldschool69.rest_with_spring_boot_and_java.config.EmailConfig;
import br.com.oldschool69.rest_with_spring_boot_and_java.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfig;

    public void sendSimpleEmail(String to, String subject, String body) {
        emailSender
                .to(to)
                .withSubject(subject)
                .withMessage(body)
                .send(emailConfig);
    }
}
