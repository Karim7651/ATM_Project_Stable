package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FrmCustomerRequestPinChange {
    public TextField txtNewPIN;
    public Label lblWarning;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }

    public void changePinRequest(ActionEvent actionEvent) {
        int newPin = 0;
        if(txtNewPIN.getText().length() != 4)
            lblWarning.setText("Enter 4 Numbers");
        try{
            newPin = Integer.parseInt(txtNewPIN.getText());
        }catch (Exception e){
            lblWarning.setText("Enter 4 Numbers");
            return;
        }
        //database update pin logic here

    }
}
