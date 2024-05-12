package com.tanassat.ebankingbackend.dtos;

import com.tanassat.ebankingbackend.entities.BankAccount;
import com.tanassat.ebankingbackend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
