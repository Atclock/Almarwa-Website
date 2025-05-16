package com.example.demo.controller;

import com.example.demo.model.Quote;
import com.example.demo.service.EmailService;
import com.example.demo.service.QuoteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/api/quotes")
public class QuoteRequestController {

    @Autowired
    private QuoteRequestService quoteRequestService;

    @Autowired
    private EmailService emailService; // Inject EmailService

    @PostMapping
    public Quote createQuoteRequest(@RequestBody Quote request) {
        Quote savedQuote = quoteRequestService.saveQuoteRequest(request);
        emailService.sendQuoteNotification(savedQuote); // Send email here
        return savedQuote;
    }

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteRequestService.getAllQuoteRequests();
    }
}
