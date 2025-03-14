package com.example.bankApplication.Repo;

import com.example.bankApplication.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<BankAccount,Long> {
}
