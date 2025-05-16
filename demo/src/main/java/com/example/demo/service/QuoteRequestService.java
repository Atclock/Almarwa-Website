package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuoteRequestService {

    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    public Quote saveQuoteRequest(Quote request) {
        return quoteRequestRepository.save(request);
    }

    public List<Quote> getAllQuoteRequests() {
        return quoteRequestRepository.findAll();
    }
}
