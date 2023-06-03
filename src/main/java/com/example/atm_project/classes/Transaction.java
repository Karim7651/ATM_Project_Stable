package com.example.atm_project.classes;

public class Transaction {
    //if it's a cash withdrawal toCardId is set to zero
    //if it's a deposit
    private long fromCardID;

    private long toCardID;
    private int amount;

    private String type;

    public Transaction(long fromCardID, long toCardID, int amount,String type) {
        this.fromCardID = fromCardID;
        this.toCardID = toCardID;
        this.amount = amount;
        this.type = type;
    }

    public long getFromCardID() {
        return fromCardID;
    }

    public long getToCardID() {
        return toCardID;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + fromCardID +
                ", to=" + toCardID +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
