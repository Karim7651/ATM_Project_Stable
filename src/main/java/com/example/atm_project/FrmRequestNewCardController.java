package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class FrmRequestNewCardController {
    public Label lblWarning;
    public TextField initialDepositField;
    public TextField CVVField;
    public TextField PINField;
    public TextField cardNumberField;
    public TextField addressField;
    public TextField nameField;

    public void submitRequest(ActionEvent actionEvent) throws SQLException {
        if (initialDepositField.getText().equals("") || CVVField.getText().equals("") || PINField.getText().equals("") || cardNumberField.getText().equals("")
                || addressField.getText().equals("") || nameField.getText().equals("")) {
            lblWarning.setText("Fill Information!");
            return;
        } else if (cardNumberField.getText().length() != 16 || PINField.getText().length() != 4 || CVVField.getText().length() != 3) {
            lblWarning.setText("Invalid Length for Fields");
            return;
        } else if (PINField.getText().charAt(0) == '0' || CVVField.getText().charAt(0) == '0') {
            lblWarning.setText("PIN and CVV Cannot start with '0' ");
            return;
        }
        try {
            long cardNumber = Long.parseLong(cardNumberField.getText());
            int PIN = Integer.parseInt(PINField.getText());
            int initialDeposit = Integer.parseInt(initialDepositField.getText());
            int CVV = Integer.parseInt(CVVField.getText());
        } catch (Exception e) {
            lblWarning.setText("Only Numbers in Pin, Number, CVV, Deposit");
            return;
        }
        if (Integer.parseInt(initialDepositField.getText()) < 50) {
            lblWarning.setText("Insufficient Deposit");
        } else if (Lists.getCardOwnerMap().containsKey(Long.parseLong(cardNumberField.getText()))) {
            lblWarning.setText("Card Number Already Registered");
        } else {
            long cardNumber = Long.parseLong(cardNumberField.getText());
            int PIN = Integer.parseInt(PINField.getText());
            int initialDeposit = Integer.parseInt(initialDepositField.getText());
            int CVV = Integer.parseInt(CVVField.getText());
            DB.addCustomer(nameField.getText(), addressField.getText(), cardNumber, PIN, CVV, initialDeposit);
            lblWarning.setText("Card Added Successfully");
        }
    }

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(0, "Please Insert Card");
    }
}
