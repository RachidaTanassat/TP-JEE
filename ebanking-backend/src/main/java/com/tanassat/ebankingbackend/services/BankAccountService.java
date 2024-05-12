package com.tanassat.ebankingbackend.services;

import com.tanassat.ebankingbackend.dtos.*;
import com.tanassat.ebankingbackend.entities.BankAccount;
import com.tanassat.ebankingbackend.entities.CurrentAccount;
import com.tanassat.ebankingbackend.entities.Customer;
import com.tanassat.ebankingbackend.entities.SavingAccount;
import com.tanassat.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.tanassat.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.tanassat.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
     CustomerDTO saveCustomer(CustomerDTO customerDTO);
     CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;

     SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;
     List<CustomerDTO> listCustomers();
     BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
     void transfer(String accountIdSource,String accountIdDestination, double amount ) throws BankAccountNotFoundException, BalanceNotSufficientException;


     List<BankAccountDTO> bankAccountList();

     CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

     void deleteCustomer(Long customerId);

     List<AccountOperationDTO> accountHistory(String accountId);

     AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
