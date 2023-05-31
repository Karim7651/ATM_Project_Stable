package com.example.atm_project.classes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Lists {
    public static Stage stage;

    public static ArrayList<Scene> scenesArrayList = new ArrayList<>();
    private static ArrayList<Administrator> administratorsList = new ArrayList<>();

    private static ArrayList<Customer> customersList = new ArrayList<>();

    private static HashMap<Long, String> cardOwnerMap = new HashMap<>();

    private static HashMap<Long, Integer> cardBalanceMap = new HashMap<>();

    private static HashMap<Long, Integer> cardCVVMap = new HashMap<>();

    private static HashMap<Long, Integer> loginMap = new HashMap<>();

    private static HashMap<Long, LocalDate> cardExpiryMap = new HashMap<>();

    private static ArrayList<String> loggedInCustomerData = new ArrayList<>();
    /* 0 -> name
     *  1 -> balance
     *  2 -> PIN*/

    //avoid making objects
    private Lists() {
    }

    public static ArrayList<Administrator> getAdministratorsList() {
        return administratorsList;
    }

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public static HashMap<Long, String> getCardOwnerMap() {
        return cardOwnerMap;
    }

    public static HashMap<Long, Integer> getCardBalanceMap() {
        return cardBalanceMap;
    }

    public static HashMap<Long, Integer> getCardCVVMap() {
        return cardCVVMap;
    }

    public static ArrayList<String> getLoggedInCustomerData() {
        return loggedInCustomerData;
    }

    public static HashMap<Long, Integer> getLoginMap() {
        return loginMap;
    }

    public static HashMap<Long, LocalDate> getCardExpiryMap() {
        return cardExpiryMap;
    }

    public static void logCustomerIn(String name,int balance, int PIN){
        loggedInCustomerData.add(name);
        loggedInCustomerData.add(String.valueOf(balance));
        loggedInCustomerData.add(String.valueOf(PIN));
    }
}
