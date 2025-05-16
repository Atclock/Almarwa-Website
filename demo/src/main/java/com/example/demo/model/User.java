package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

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
    private String gradePurityType;
    private String quantityRequired;
    private String website;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getGradePurityType() { return gradePurityType; }
    public void setGradePurityType(String gradePurityType) { this.gradePurityType = gradePurityType; }

    public String getQuantityRequired() { return quantityRequired; }
    public void setQuantityRequired(String quantityRequired) { this.quantityRequired = quantityRequired; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}
