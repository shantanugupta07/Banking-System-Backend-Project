package com.example.BankingSystemProject.Services;
import com.example.BankingSystemProject.Entities.Account;
import com.example.BankingSystemProject.Entities.Customer;
import com.example.BankingSystemProject.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Account createAccount(Long customerId, Account account) {
        Customer customer = customerService.findCustomerById(customerId);
        account.setCustomer(customer);
        account.setBalance(BigDecimal.ZERO); // Initial balance
        return accountRepository.save(account);
    }

    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }
}
