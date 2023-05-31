package com.example.atm_project.classes;

import java.time.LocalDate;
import java.util.ArrayList;


public class Card {
    private long cardNumber;
    private int pin;
    private int cvv;
    private String cardOwner;
    private double balance;
    private LocalDate expiryDate;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Card(long cardNumber, int pin, int cvv, double balance,String cardOwner) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.cvv = cvv;
        this.balance = balance;
        this.cardOwner = cardOwner;
        LocalDate now = LocalDate.now();
        this.expiryDate = now.plusYears(5);
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void changeBalance(int value){
        this.balance = this.balance + value;
    }

    public int getCvv() {
        return cvv;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    public boolean isExpired() {
        // now - expiryDate = positive (means card expired)
        LocalDate now = LocalDate.now();
        int test = now.compareTo(this.expiryDate);
        if (test > 0) {
            return true;
        }
        return false;
    }

}
