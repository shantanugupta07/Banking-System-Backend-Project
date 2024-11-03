package com.example.BankingSystemProject.Controller;

import com.example.BankingSystemProject.Entities.Transaction;
import com.example.BankingSystemProject.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.YearMonth;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping("/withdrawal")
    public Transaction withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }
    @GetMapping("/statements")
    public List<Transaction> getMonthlyStatement(@RequestParam Long accountId, @RequestParam int year, @RequestParam int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return transactionService.getMonthlyStatement(accountId, yearMonth);
    }
}
