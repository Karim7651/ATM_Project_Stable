package com.example.atm_project;

import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FrmLoginController {
    public Button btnLogin;
    public Button btnNewCard;
    public PasswordField txtCardPin;
    public TextField txtCardID;
    public Label lblWarning;


    public void loginRequest(ActionEvent actionEvent) {
        long cardIdNum = 0;
        int cardPinNum = 0;
        if (txtCardID.getText().equals("admin") && txtCardPin.getText().equals("admin")) {
            ATM.showSceneIndex(8, "Check Recordings");
        } else if (txtCardID.getText().equals("") || txtCardPin.getText().equals("")) {
            System.out.println(txtCardID.getText());
            lblWarning.setText("Make Sure to Fill all Fields");
        } else if (txtCardID.getText().length() != 16 || txtCardPin.getText().length() != 4) {
            lblWarning.setText("Invalid Input");
        } else if (txtCardID.getText().length() == 16 || txtCardPin.getText().length() == 4) {
            String cardId = txtCardID.getText();
            String cardPin = txtCardPin.getText();
            try {
                cardIdNum = Long.parseLong(cardId);
                cardPinNum = Integer.parseInt(cardPin);
            } catch (Exception e) {
                lblWarning.setText("Only Numbers Allowed!");
                return;
            }
            if (Lists.getLoginMap().containsKey(cardIdNum) && Lists.getLoginMap().get(cardIdNum).equals(cardPinNum)) {
                ATM.showSceneIndex(1, "Welcome, Choose an Option");
                Lists.getLoggedInCustomerData().add(Lists.getCardOwnerMap().get(cardIdNum));
                Lists.getLoggedInCustomerData().add(Lists.getCardBalanceMap().get(cardIdNum).toString());
                Lists.getLoggedInCustomerData().add(Lists.getLoginMap().get(cardIdNum).toString());
                String name = Lists.getCardOwnerMap().get(cardIdNum);
                int balance = Lists.getCardBalanceMap().get(cardIdNum);
                int pin = Lists.getLoginMap().get(cardIdNum);
                Lists.getLoggedInCustomerData().add(name);
                Lists.getLoggedInCustomerData().add(String.valueOf(balance));
                Lists.getLoggedInCustomerData().add(String.valueOf(pin));
                /*FrmCustomerController.getLblWelcome().setText();
                FrmCustomerController.getLblBalance().setText();*/


            } else {
                lblWarning.setText("Invalid Credentials");
            }
        }
    }

    public void newCardRequest(ActionEvent actionEvent) throws IOException {
        ATM.showSceneIndex(6, "Fill Information");
    }
}