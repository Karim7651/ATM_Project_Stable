package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FrmCustomerWithdrawController {
    public Label lblWarning;
    public TextField txtAmount;

    public void withdrawRequest(ActionEvent actionEvent) {
        int currentBalance = 0;
        Long cardNumber =  0L;
        int amount = 0;
        //withdraw logic goes here
        if(txtAmount.getText().equals("")){
            lblWarning.setText("Enter Amount");
            return;
        }
        try{
            currentBalance = Integer.parseInt(Lists.getLoggedInCustomerData().get(1));
            cardNumber =  Long.parseLong(Lists.getLoggedInCustomerData().get(3));
            amount = Integer.parseInt(txtAmount.getText());
        }catch (Exception e){
            lblWarning.setText("Only numbers allowed");
            return;
        }
        if(currentBalance - amount >= 0){
            DB.decreaseBalance(cardNumber,amount);
            int newBalance = currentBalance-amount;
            DB.addTransaction(cardNumber,cardNumber,amount,"Withdraw");
            lblWarning.setText("Withdraw Successful \n" + "Balance is :$"  + newBalance );
        }
        else {
            lblWarning.setText("Not enough balance \n balance is :$" + currentBalance);
        }
    }

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }
    public void refreshLbl(MouseEvent mouseEvent) {
        lblWarning.setText("Balance : $" + Lists.getLoggedInCustomerData().get(1));
    }
    public void handleEnterKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            withdrawRequest(new ActionEvent());
        }
    }
}
