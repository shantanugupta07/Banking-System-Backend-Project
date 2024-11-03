package com.example.BankingSystemProject.Repositories;

import com.example.BankingSystemProject.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {

}
