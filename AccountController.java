package com.example.BankingSystemProject.Controller;
import com.example.BankingSystemProject.Entities.Account;
import com.example.BankingSystemProject.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/{customerId}")
    public Account createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        return accountService.createAccount(customerId, account);
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
        return accountService.checkBalance(id);
    }
}
