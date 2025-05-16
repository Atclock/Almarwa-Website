package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.model.Quote;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendQuoteNotification(Quote quote) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("yousofabuz12@gmail.com");
        message.setSubject("New Quote Submission");
        message.setText(
            "New quote submitted:\n\n" +
            "Name: " + quote.getFirstName() + " " + quote.getLastName() + "\n" +
            "Company: " + quote.getCompany() + "\n" +
            "Email: " + quote.getEmail() + "\n" +
            "Phone: " + quote.getPhoneNumber() + "\n" +
            "Product: " + quote.getProductName()
        );
        mailSender.send(message);
    }
}
