package com.example.atm_project;

import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FrmCustomerRequestTransactionController {


    public TextField lblCardTo;
    public TextField lblAmount;
    public Label lblWarning;

    public double userBalance = 1000;

    public void sendTransactionRequest(ActionEvent actionEvent) {
        if (lblCardTo.getText().equals("") || lblAmount.getText().equals("")) {
            lblWarning.setText("Make Sure to Fill all Fields");
        } else if (lblCardTo.getText().length() != 16 || lblAmount.getText().length() != 4) {
            lblWarning.setText("Invalid Input");
        } else if (lblCardTo.getText().length() == 16) {
            String cardId = lblCardTo.getText();
            String amountString = lblAmount.getText();
            int cardIdNum = 0;
            double amount = 0;
            try {
                cardIdNum = Integer.parseInt(cardId);
                amount = Integer.parseInt(amountString);
            } catch (Exception e) {
                lblWarning.setText("Only Numbers Allowed!");
            }
            if(userBalance < amount)
                lblWarning.setText("Only Numbers Allowed!");
            else{
                lblWarning.setText("Transaction Complete");
                //logic to transaction here
            }


        }
    }
    public void goBackRequest (ActionEvent actionEvent){
        Lists.stage.setScene(Lists.scenesArrayList.get(1));
        Lists.stage.setTitle("Choose an Option");
    }
}
