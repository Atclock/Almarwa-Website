package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quote_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String area;
    private String phoneNumber;
    private String productName;
    private String gradePurityType;  // Optional
    private String quantityRequired; // Optional
    private String website;          // Optional
}
