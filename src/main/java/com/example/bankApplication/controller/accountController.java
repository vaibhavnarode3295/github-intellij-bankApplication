package com.example.bankApplication.controller;

import com.example.bankApplication.model.BankAccountVo;
import com.example.bankApplication.services.AccountServices;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class accountController {
    @Autowired
    private AccountServices accountServices;
    @GetMapping("/")
    public ModelAndView firstPage()
    {
        return new ModelAndView("form.html");
    }
    @PostMapping("/adduser")
    public ModelAndView addUser(@RequestBody BankAccountVo bankAccountVo)
    {
        accountServices.createUser(bankAccountVo);
        return new ModelAndView("success");
    }
    @PutMapping("/deposit")
    public ModelAndView deposit(@PathVariable Long accountNumber, @PathVariable Long amount)
    {
        accountServices.deposit(accountNumber,amount);
        return new ModelAndView("depositSuccess");
    }
    @PutMapping("/withdraw")
    public ModelAndView withdraw(@PathVariable Long accountNumber,@PathVariable Long amount)
    {
        accountServices.withdraw(accountNumber,amount);
        return new ModelAndView("withdrawSuccess");
    }
    @GetMapping("/balance")
    public ModelAndView balance(@PathVariable Long accountNumber)
    {
       double balance = accountServices.checkBalance(accountNumber);
       return new ModelAndView("seeBalance", Map.of("balance",balance));
    }
}
