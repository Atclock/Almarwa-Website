package com.example.demo.repository;

import com.example.demo.model.Quote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRequestRepository extends JpaRepository<Quote, Long> {
    List<Quote> findAllByEmail(String email);
}
