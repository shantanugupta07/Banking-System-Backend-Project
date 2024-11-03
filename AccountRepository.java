package com.example.BankingSystemProject.Repositories;

import com.example.BankingSystemProject.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
