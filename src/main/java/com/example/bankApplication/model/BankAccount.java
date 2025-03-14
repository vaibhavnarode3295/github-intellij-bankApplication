package com.example.bankApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bank")
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue
    private Long accountNumber;
    private String accountHolderName;
    private Long balance;
    private String email;
    private String gender;
    private String city;
}
