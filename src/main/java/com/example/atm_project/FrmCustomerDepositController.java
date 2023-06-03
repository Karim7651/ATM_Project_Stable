package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FrmCustomerDepositController {
    public TextField txtBoxAmount;
    public Label lblWarning;



    public void confirmDepositRequest(ActionEvent actionEvent) {
        int currentBalance = 0;
        Long cardNumber =  0L;
        int amount=0;
        if(txtBoxAmount.getText().equals("")){
            lblWarning.setText("Enter Amount");
            return;
        }
        try {
            currentBalance = Integer.parseInt(Lists.getLoggedInCustomerData().get(1));
            cardNumber =  Long.parseLong(Lists.getLoggedInCustomerData().get(3));
            amount = Integer.parseInt(txtBoxAmount.getText());
        }catch (Exception e){
            lblWarning.setText("Only Numbers!");
            return;
        }
        if(amount > 20000){
            lblWarning.setText("Too much!");
        } else {
            DB.increaseBalance(cardNumber,amount);
            DB.addTransaction(cardNumber,cardNumber,amount,"Deposit");
            int nextBalance = currentBalance + amount;
            lblWarning.setText("Successful Deposit! \n" + "balance is : $" + nextBalance);
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
            confirmDepositRequest(new ActionEvent());
        }
    }
}
