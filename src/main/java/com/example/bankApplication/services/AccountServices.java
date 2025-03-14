package com.example.bankApplication.services;

import com.example.bankApplication.Repo.AccountRepo;
import com.example.bankApplication.model.BankAccount;
import com.example.bankApplication.model.BankAccountVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class AccountServices {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    public void createUser(BankAccountVo bankAccountVo)
    {
        BankAccount bankAccount=new BankAccount();
        bankAccount.setAccountHolderName(bankAccountVo.getAccountHolderName());
        bankAccount.setBalance(bankAccountVo.getBalance());
        bankAccount.setEmail(bankAccountVo.getEmail());
        bankAccount.setGender(bankAccountVo.getGender());
        bankAccount.setCity(bankAccountVo.getCity());
        accountRepo.save(bankAccount);
        String toemail = bankAccountVo.getEmail();
        String body ="Hello, "+bankAccountVo.getAccountHolderName()+"Welcome to ABC bank"+"\n\nYour Name:"+bankAccount.getAccountHolderName()+"\nYour Account Number:"+bankAccount.getAccountNumber()+"\n Account Balance:"+bankAccount.getBalance()+"\nGender:"+bankAccount.getGender()+"\nCity:"+bankAccount.getCity()+"\nYour Account Created Successfully";
        sendMail(toemail,"Welcome",body);
    }

    public void sendMail(String toemail,String subject,String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("vaibhavnarode01@gmail.com");
        message.setTo(toemail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @Transactional
    public void deposit(Long accountNumber,Long amount)
    {
        BankAccount account = accountRepo.findById(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()+amount);
        accountRepo.save(account);
        String toemail=account.getEmail();
        String body = "Hi, "+account.getAccountHolderName()+"\n\nYour Amount "+amount+" is Credited Successfully";
        String subject="Deposit";
        sendMail(toemail,subject,body);
    }

    public void withdraw(Long accountNumber, Long amount)
    {
        BankAccount account = accountRepo.findById(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()-amount);
        accountRepo.save(account);
        String toemail=account.getEmail();
        String body = "Hi, "+account.getAccountHolderName()+"\n\nYour Amount "+amount+" is Debited Successfully";
        String subject="Withdraw";
        sendMail(toemail,subject,body);
    }

    public double checkBalance(Long accountNumber)
    {
        BankAccount account = accountRepo.findById(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        String toemail=account.getEmail();
        String body = "Hi, "+account.getAccountHolderName()+"\nYour balance is "+account.getBalance();
        String subject="See Balance";
        sendMail(toemail,subject,body);
        return account.getBalance();
    }
}
