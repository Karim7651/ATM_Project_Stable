package com.example.atm_project;

import com.example.atm_project.classes.Lists;
import com.example.atm_project.classes.Transaction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class FrmAdminPanelController {
    int counter = 0;


    public ListView listView;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(0, "Insert Card");
    }

    public void showTransaction(MouseEvent mouseEvent) {
        if(counter == 0 ){
            ArrayList<Transaction> transactions = Lists.getTransactions();
            listView.setItems(FXCollections.observableArrayList(transactions));
            counter++;
        }
        }
}

