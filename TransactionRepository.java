package com.example.BankingSystemProject.Repositories;
import com.example.BankingSystemProject.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.timestamp >= :startDateTime AND t.timestamp <= :endDateTime")
    List<Transaction> findTransactionsByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime);


}

/*
import com.example.BankingSystemProject.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.date >= :startDate AND t.date <= :endDate")
    List<Transaction> findTransactionsByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);



}

 */

/*
import com.example.BankingSystemProject.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsByAccountIdAndDateRange(@Param("accountId") Long accountId,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate);
}

 */


