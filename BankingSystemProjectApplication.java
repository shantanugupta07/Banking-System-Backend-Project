package com.example.BankingSystemProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
public class BankingSystemProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemProjectApplication.class, args);
	}

}
