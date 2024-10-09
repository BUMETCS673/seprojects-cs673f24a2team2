package edu.bu.cs673.secondhand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceInterface implements edu.bu.cs673.secondhand.serviceInterface.EmailServiceInterface {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.url}")
    private String appUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendSignUpEmail(String to, String token) {
        String subject = "Complete Your Sign-Up for SecondHand";
        String text = "Please click on the following link to complete your sign-up: "
                + appUrl + "/api/users/complete-signup?email=" + to + "&token=" + token
                + "\n\nThis link will expire in 24 hours.";
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendActivationEmail(String email, String activationToken) {
        // Implement this method if needed
    }
}
