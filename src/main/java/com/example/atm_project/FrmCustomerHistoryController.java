package com.example.atm_project;

import com.example.atm_project.classes.Lists;
import com.example.atm_project.classes.Transaction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;



import java.util.ArrayList;


public class FrmCustomerHistoryController {


    public ListView listView;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1, "Welcome, Choose an Option");

    }


    public void showTransactions(MouseEvent mouseEvent) {

        long currentCard = Long.parseLong(Lists.getLoggedInCustomerData().get(3));
        ArrayList<Transaction> transactions = Lists.getTransactions();
        ArrayList<Transaction> currentTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getFromCardID() == currentCard || transaction.getToCardID() == currentCard) {
                currentTransactions.add(transaction);
            }
        }
        listView.setItems(FXCollections.observableArrayList(currentTransactions));

    }
}

