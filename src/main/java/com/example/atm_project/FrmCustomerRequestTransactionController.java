package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FrmCustomerRequestTransactionController {


    public TextField lblCardTo;
    public TextField lblAmount;
    public Label lblWarning;


    public void sendTransactionRequest(ActionEvent actionEvent) {
        long currentCardId = Long.parseLong(Lists.getLoggedInCustomerData().get(3));
        if (lblCardTo.getText().equals("") || lblAmount.getText().equals("")) {
            lblWarning.setText("Make Sure to Fill all Fields");
        }
        String cardIdString = lblCardTo.getText();
        String amountString = lblAmount.getText();
        long cardIdToNum = 0;
        int amount = 0;
        int userBalance = Integer.parseInt(Lists.getLoggedInCustomerData().get(1));
        try {
            cardIdToNum = Long.parseLong(cardIdString);
            amount = Integer.parseInt(amountString);
        } catch (Exception e) {
            lblWarning.setText("Only Numbers Allowed!");
            return;
        }
        //validate input
        if (lblCardTo.getText().length() != 16 || !Lists.getLoginMap().containsKey(Long.parseLong(lblCardTo.getText()))) {
            lblWarning.setText("Invalid Card number");
        } else if (currentCardId == cardIdToNum) {
            lblWarning.setText("You Cannot Do a Transaction To/From The Same Card");
        } else if (lblCardTo.getText().length() == 16) {
            if (userBalance < amount)
                lblWarning.setText("Not Enough Balance");
            else {
                DB.addTransaction(currentCardId, cardIdToNum, amount, "Transaction");
                DB.decreaseBalance(currentCardId, amount);
                DB.increaseBalance(cardIdToNum, amount);
                int currentBalance = Lists.getCardBalanceMap().get(currentCardId);
                lblWarning.setText("Transaction Complete \n" + "balance is : $" + currentBalance);
            }
        }
    }

    public void goBackRequest(ActionEvent actionEvent) {
        Lists.stage.setScene(Lists.scenesArrayList.get(1));
        Lists.stage.setTitle("Choose an Option");
    }
}
