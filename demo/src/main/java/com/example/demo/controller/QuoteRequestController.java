package com.example.demo.controller;

import com.example.demo.util.RateLimiter;
import com.example.demo.model.Quote;
import com.example.demo.service.EmailService;
import com.example.demo.service.QuoteRequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/api/quotes")
public class QuoteRequestController {

    @Autowired
    private QuoteRequestService quoteRequestService;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Map<String, Object> createQuoteRequest(@Valid @RequestBody Quote request,
                                                  BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        // If validation fails
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("message", result.getAllErrors().get(0).getDefaultMessage());
            return response;
        }

        // Save and send email
        Quote savedQuote = quoteRequestService.saveQuoteRequest(request);
        emailService.sendQuoteNotification(savedQuote);

        response.put("status", "success");
        response.put("message", "Quote request submitted successfully!");
        response.put("quote", savedQuote);

        return response;
    }

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteRequestService.getAllQuoteRequests();
    }

@Autowired
private RateLimiter rateLimiter;

@PostMapping
public Map<String, Object> createQuoteRequest(@Valid @RequestBody Quote request,
                                              BindingResult result,
                                              HttpServletRequest httpRequest) {
    Map<String, Object> response = new HashMap<>();

    String clientIp = httpRequest.getRemoteAddr();

    // Rate limit check
    if (!rateLimiter.isAllowed(clientIp)) {
        response.put("status", "error");
        response.put("message", "You can only submit one request every 20 minutes.");
        return response;
    }

    // Validation check
    if (result.hasErrors()) {
        response.put("status", "error");
        response.put("message", result.getAllErrors().get(0).getDefaultMessage());
        return response;
    }

    Quote savedQuote = quoteRequestService.saveQuoteRequest(request);
    emailService.sendQuoteNotification(savedQuote);

    response.put("status", "success");
    response.put("message", "Quote request submitted successfully!");
    response.put("quote", savedQuote);

    return response;
}

}
