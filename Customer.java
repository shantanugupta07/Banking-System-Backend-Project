package com.example.BankingSystemProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    // Additional fields can be added as needed
}
