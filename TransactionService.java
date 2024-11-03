package com.example.BankingSystemProject.Services;
import com.example.BankingSystemProject.Entities.Account;
import com.example.BankingSystemProject.Entities.Transaction;
import com.example.BankingSystemProject.Repositories.AccountRepository;
import com.example.BankingSystemProject.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

//import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Transaction deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(Transaction.TransactionType.WITHDRAWAL);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);
        transaction.setAmount(amount);
        transaction.setType(Transaction.TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
    /*
    public List<Transaction> getMonthlyStatement(Long accountId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        return transactionRepository.findTransactionsByAccountIdAndDateRange(accountId, startDate, endDate);
    }
     */
    public List<Transaction> getMonthlyStatement(Long accountId, YearMonth month) {
        // Start at the beginning of the first day of the month
        LocalDateTime startDateTime = month.atDay(1).atStartOfDay();
        // End at the last moment of the last day of the month
        LocalDateTime endDateTime = month.atEndOfMonth().atTime(23, 59, 59);
        return transactionRepository.findTransactionsByAccountIdAndDateRange(accountId, startDateTime, endDateTime);
    }
}

