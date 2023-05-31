package com.example.atm_project.classes;

public class Transaction {
    //if it's a cash withdrawal toCardId is set to zero
    private int fromCardID;

    private int toCardID;
    private double amount;

    public Transaction(int fromCardID, int toCardID, double amount) {
        this.fromCardID = fromCardID;
        this.toCardID = toCardID;
        this.amount = amount;
    }

    public int getFromCardID() {
        return fromCardID;
    }

    public int getToCardID() {
        return toCardID;
    }

    public double getAmount() {
        return amount;
    }
}
