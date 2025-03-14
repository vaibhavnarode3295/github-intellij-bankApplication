package com.example.bankApplication.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BankAccountVo {
    public Long accountNumber;
    private String accountHolderName;
    private Long balance;
    @Email
    private String email;
    private String gender;
    private String city;
}
