package com.tanassat.ebankingbackend;

import com.tanassat.ebankingbackend.dtos.BankAccountDTO;
import com.tanassat.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.tanassat.ebankingbackend.dtos.CustomerDTO;
import com.tanassat.ebankingbackend.dtos.SavingBankAccountDTO;
import com.tanassat.ebankingbackend.entities.*;
import com.tanassat.ebankingbackend.enums.AccountStatus;
import com.tanassat.ebankingbackend.enums.OperationType;
import com.tanassat.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.tanassat.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.tanassat.ebankingbackend.exceptions.CustomerNotFoundException;
import com.tanassat.ebankingbackend.repositories.AccountOperationRepository;
import com.tanassat.ebankingbackend.repositories.BankAccountRepository;
import com.tanassat.ebankingbackend.repositories.CustomerRepository;
import com.tanassat.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
   // @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
     return args -> {
         Stream.of("Hassan", "Yassine", "Aicha").forEach(name->{
             Customer customer=new Customer();
             customer.setName(name);
             customer.setEmail(name+"@gmail.com");
             customerRepository.save(customer);
         });
         customerRepository.findAll().forEach(customer -> {
             CurrentAccount currentAccount = new CurrentAccount();
             currentAccount.setId(UUID.randomUUID().toString());
             currentAccount.setBalance(Math.random()*90000);
             currentAccount.setCreatedAt(new Date());
             currentAccount.setStatus(AccountStatus.CREATED);
             currentAccount.setCustomer(customer);
             currentAccount.setOverDraft(9000);
             bankAccountRepository.save(currentAccount);

             SavingAccount savingAccount = new SavingAccount();
             savingAccount.setId(UUID.randomUUID().toString());
             savingAccount.setBalance(Math.random()*90000);
             savingAccount.setCreatedAt(new Date());
             savingAccount.setStatus(AccountStatus.CREATED);
             savingAccount.setCustomer(customer);
             savingAccount.setInterestRate(5.5);
             bankAccountRepository.save(savingAccount);
         });

         bankAccountRepository.findAll().forEach(acc->{
             for(int i=0; i<5 ;i++){
                 AccountOperation accountOperation = new AccountOperation();
                 accountOperation.setOperationDate(new Date());
                 accountOperation.setAmount(Math.random()*1200);
                 accountOperation.setType(Math.random()<0.5? OperationType.CREDIT:OperationType.DEBIT);
                 accountOperation.setBankAccount(acc);
                 accountOperationRepository.save(accountOperation);
             }
         });
     };
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed").forEach(name->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });

            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000,  customer.getId(), 9000);
                    bankAccountService.saveSavingBankAccount(Math.random()*9000,  customer.getId(), 5.6);

                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccounts){
                for(int i = 0; i < 10; i++ ){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }else {
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();

                    }
                    bankAccountService.credit(accountId, 10000+Math.random()*12000, "Credit");
                    bankAccountService.debit(accountId, 1000+Math.random()*12000, "Debit");
                }
            }
        };
    }


}
