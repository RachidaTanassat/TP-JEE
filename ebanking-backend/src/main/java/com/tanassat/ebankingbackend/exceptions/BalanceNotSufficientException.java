package com.tanassat.ebankingbackend.exceptions;

public class BalanceNotSufficientException extends Exception{


    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
