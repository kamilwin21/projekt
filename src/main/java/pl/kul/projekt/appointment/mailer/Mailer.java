package pl.kul.projekt.appointment.mailer;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import pl.kul.projekt.appointment.mailer.googleauth.client.GoogleOAuthFacade;
import pl.kul.projekt.config.ApplicationConfig;

import java.util.Properties;

@Component
@RequiredArgsConstructor
public class Mailer {

    private final ApplicationConfig applicationConfig;
    private final GoogleOAuthFacade googleOAuthFacade;

    public JavaMailSenderImpl getMailer() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = mailSender.getJavaMailProperties();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(applicationConfig.getEmailUsername());
        mailSender.setPassword(googleOAuthFacade.getToken().getAccessToken());
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "false");
        return mailSender;
    }

    public SimpleMailMessage getMessage(String recipient, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(applicationConfig.getEmailUsername());
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);

        return message;
    }

}
