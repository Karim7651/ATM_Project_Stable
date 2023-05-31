package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FrmCustomerDepositController {
    public TextField txtBoxAmount;
    public Label lblWarning;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }

    public void confirmDepositRequest(ActionEvent actionEvent) {
        int amount=0;
        try {
            amount = Integer.parseInt(txtBoxAmount.getText());
        }catch (Exception e){
            lblWarning.setText("Only Numbers!");
            return;
        }
        if(amount > 20000){
            lblWarning.setText("Too much!");
        } else {
            lblWarning.setText("Successful Deposit!");
            // adding balance logic and database update here.
        }

    }
}
