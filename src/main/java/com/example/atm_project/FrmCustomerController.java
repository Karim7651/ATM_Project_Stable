package com.example.atm_project;


import com.example.atm_project.classes.Lists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmCustomerController {

    @FXML
    private ImageView imgViewTransaction;

    @FXML
    private  Label lblBalance;

    @FXML
    private  Label lblWelcome ;

    @FXML
    private ImageView imgViewWithdraw;

    @FXML
    private ImageView imgViewHistory;



    @FXML
    void transactionRequest() throws IOException {
        ATM.showSceneIndex(2, "Insert Deatils");
    }

    @FXML
    void requestHistory() throws IOException {
        ATM.showSceneIndex(3, "Here is all Transaction History");
    }

    @FXML
    void withdrawRequest() throws IOException {
        ATM.showSceneIndex(4, "Choose Amount");
    }

    @FXML
    void requestPinChange() throws IOException {
        ATM.showSceneIndex(5, "Chose your new PIN");
    }



    public void requestDeposit() {
        ATM.showSceneIndex(7, "Choose Amount");
    }


    public void setLbl(MouseEvent mouseEvent) {
        lblBalance.setText("$" + Lists.getLoggedInCustomerData().get(1));
        lblWelcome.setText("Welcome " + Lists.getLoggedInCustomerData().get(0));
    }
}
