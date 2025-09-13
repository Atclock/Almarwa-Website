package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.model.Quote;
import com.example.demo.util.Sanitizers;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Prevent email header injection
    private String stripHeaderBreaks(String s) {
        return s == null ? null : s.replaceAll("[\\r\\n]", "");
    }

    public void sendQuoteNotification(Quote quote) {
        SimpleMailMessage message = new SimpleMailMessage();

        // Secure recipient and subject
        message.setTo("yousofabuz12@gmail.com"); // internal recipient
        message.setSubject(stripHeaderBreaks("New Quote Submission"));

        // Sanitize all user data before putting it in the email body
        String body =
            "New quote submitted:\n\n" +
            "Name: " + Sanitizers.plain(quote.getFirstName()) + " " + Sanitizers.plain(quote.getLastName()) + "\n" +
            "Company: " + Sanitizers.plain(quote.getCompany()) + "\n" +
            "Email: " + stripHeaderBreaks(Sanitizers.plain(quote.getEmail())) + "\n" +
            "Phone: " + Sanitizers.plain(quote.getPhoneNumber()) + "\n" +
            "Product: " + Sanitizers.plain(quote.getProductName()) + "\n" +
            "Grade/Purity/Type: " + Sanitizers.plain(quote.getGradePurityType()) + "\n" +
            "Quantity Required: " + Sanitizers.plain(quote.getQuantityRequired()) + "\n" +
            "Website: " + Sanitizers.plain(quote.getWebsite());

        message.setText(body);

        mailSender.send(message);
    }
}

