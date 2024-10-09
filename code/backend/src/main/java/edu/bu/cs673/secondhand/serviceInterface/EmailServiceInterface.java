package edu.bu.cs673.secondhand.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendSignUpEmail(String to, String token);
    void sendActivationEmail(String email, String activationToken);
}
