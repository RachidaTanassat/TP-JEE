package com.tanassat.ebankingbackend.repositories;

import com.tanassat.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
