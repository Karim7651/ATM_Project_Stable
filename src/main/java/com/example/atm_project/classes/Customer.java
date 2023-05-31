package com.example.atm_project.classes;

import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<Card> cards = new ArrayList<Card>();


    public Customer(String name, String address) {
        super(name, address);
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

}
