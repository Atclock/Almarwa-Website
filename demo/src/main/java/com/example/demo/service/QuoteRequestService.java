package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRequestRepository;
import com.example.demo.util.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteRequestService {

    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    public Quote saveQuoteRequest(Quote request) {
        // sanitize all user fields before saving
        request.setFirstName(Sanitizers.plain(request.getFirstName()));
        request.setLastName(Sanitizers.plain(request.getLastName()));
        request.setCompany(Sanitizers.plain(request.getCompany()));
        request.setEmail(Sanitizers.plain(request.getEmail()));
        request.setArea(Sanitizers.plain(request.getArea()));
        request.setPhoneNumber(Sanitizers.plain(request.getPhoneNumber()));
        request.setProductName(Sanitizers.plain(request.getProductName()));
        request.setGradePurityType(Sanitizers.plain(request.getGradePurityType()));
        request.setQuantityRequired(Sanitizers.plain(request.getQuantityRequired()));
        request.setWebsite(Sanitizers.plain(request.getWebsite()));

        return quoteRequestRepository.save(request);
    }

    public List<Quote> getAllQuoteRequests() {
        return quoteRequestRepository.findAll();
    }
}

