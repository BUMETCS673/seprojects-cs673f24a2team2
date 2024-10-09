package edu.bu.cs673.secondhand.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendSimpleMessage() {
        try {
            emailService.sendSimpleMessage("qyyh@bu.edu", "Test Subject", "Test Content");
        } catch (MailException e) {
            e.printStackTrace(); // 打印完整的堆栈跟踪
            fail("Failed to send email: " + e.getMessage() + "\nCause: " + e.getCause());
        }
    }
}
