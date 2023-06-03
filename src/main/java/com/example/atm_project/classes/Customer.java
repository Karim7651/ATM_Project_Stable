package com.example.atm_project.classes;

import java.util.ArrayList;

public class Customer extends Person {

    private static Customer customer;
    private long currentCard;

    /*private Customer(String name, String address,) {
        super(name, address);
    }*/
    private Customer(long currentCard){
        this.currentCard = currentCard;
    }

    public static Customer getInstance(long currentCard){
        if(customer == null){
            customer = new Customer(currentCard);
            return customer;
        }
        return customer;
    }
}
