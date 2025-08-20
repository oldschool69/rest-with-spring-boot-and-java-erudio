package br.com.oldschool69.rest_with_spring_boot_and_java.mail;

import br.com.oldschool69.rest_with_spring_boot_and_java.config.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@Component
public class EmailSender implements Serializable {

    Logger logger = LoggerFactory.getLogger(EmailSender.class);
    private final JavaMailSender mailSender;
    private String to;
    private String subject;
    private String body;
    private ArrayList<InternetAddress> recipients = new ArrayList<>();
    private File attachment;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public EmailSender to(String to) {
        this.to = to;
        this.recipients = getRecipientes(to);
        return this;
    }


    public EmailSender withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailSender withMessage(String body) {
        this.body = body;
        return this;
    }

    public EmailSender attach(String fileDir) {
        this.attachment = new File(fileDir);
        return this;
    }

    public void send(EmailConfig config) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(config.getUsername());
            helper.setTo(recipients.toArray(new InternetAddress[0]));
            helper.setSubject(subject);
            helper.setText(body, true);
            if (attachment != null) {
                helper.addAttachment(attachment.getName(), attachment);
            }
            mailSender.send(message);
            logger.info("Email sent to %s with the subject '%s'%n", to, subject);
            reset();
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending the email", e);
        }
    }

    private void reset() {
        this.to = null;
        this.subject = null;
        this.body = null;
        this.recipients = null;
        this.attachment = null;
    }

    private ArrayList<InternetAddress> getRecipientes(String to) {
        String toWithoutSpaces = to.replaceAll("\\s", "");
        StringTokenizer tok = new StringTokenizer(toWithoutSpaces, ";");
        ArrayList<InternetAddress> recipientList = new ArrayList<>();
        while (tok.hasMoreElements()){
            try {
                recipientList.add(new InternetAddress(tok.nextElement().toString()));
            } catch (AddressException e) {
                throw new RuntimeException(e);
            }
        }
        return recipientList;
    }
}
