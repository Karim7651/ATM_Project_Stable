package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class FrmCustomerRequestPinChange {
    public TextField txtNewPIN;
    public Label lblWarning;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }

    public void changePinRequest(ActionEvent actionEvent) throws SQLException {
        int newPin = 0;
        if(txtNewPIN.getText().equals("")){
            lblWarning.setText("Enter New Pin");
        }
        else if(txtNewPIN.getText().length() != 4){
            lblWarning.setText("Enter 4 numbers");
        }
        //if pin is the same
        else if (txtNewPIN.getText().equals(Lists.getLoggedInCustomerData().get(2))){
            lblWarning.setText("Pin Didn't change");
        }
        else{
            try{
                newPin = Integer.parseInt(txtNewPIN.getText());
            }catch (Exception e){
                lblWarning.setText("Enter 4 Numbers");
                return;
            }
            long cardNumber = Long.parseLong(Lists.getLoggedInCustomerData().get(3));
            //update data locally
            Lists.getLoggedInCustomerData().remove(2);
            Lists.getLoggedInCustomerData().add(2,String.valueOf(newPin));
            Lists.getLoginMap().put(cardNumber,newPin);
            //database update pin logic here
            System.out.println(Lists.getLoggedInCustomerData());
            DB.changePin(cardNumber,newPin);
            lblWarning.setText("PIN Updated Successfully");
        }
    }
}
