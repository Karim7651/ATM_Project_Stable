package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class FrmCustomerWithdrawController {
    public Label lblWarning;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }

    public void withdrawRequest(ActionEvent actionEvent) {
        //withdraw logic goes here
    }
}
